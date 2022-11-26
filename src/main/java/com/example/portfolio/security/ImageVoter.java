package com.example.portfolio.security;

import com.example.portfolio.domain.image.Image;
import com.example.portfolio.error.NotFoundException;
import com.example.portfolio.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ImageVoter implements AccessDecisionVoter<FilterInvocation> {
  private static final Pattern PATTERN = Pattern.compile("/api/(v.+)/images/(.+)");
  private final ImageService service;

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.isAssignableFrom(FilterInvocation.class);
  }

  @Override
  public int vote(Authentication authentication, FilterInvocation invocation, Collection<ConfigAttribute> attributes) {
    final String method = invocation.getHttpRequest().getMethod();
    final Matcher matcher = PATTERN.matcher(invocation.getRequestUrl());

    if(!checkAdequateSubject(matcher)) return ACCESS_ABSTAIN;

    try{
      Image image = findImage(matcher);
      if(image==null){
        return ACCESS_DENIED;
      }

      if("GET".equals(method)){
        if(isAnonymous(authentication)) return ACCESS_DENIED;
        if(isOwned(image, authentication)) return ACCESS_GRANTED;
      }
      if(
        "POST".equals(method)||
        "PUT".equals(method)||
        "PATCH".equals(method)||
        "DELETE".equals(method)
      ){
        if(image.isPublic()) return ACCESS_GRANTED;
        if(isAnonymous(authentication)) return ACCESS_DENIED;
        if(isOwned(image, authentication)) return ACCESS_GRANTED;
      }
      return  ACCESS_DENIED;
    }catch (NotFoundException e){
      return ACCESS_DENIED;
    }
  }

  private boolean checkAdequateSubject(Matcher matcher){
    return matcher.matches();
  }

  private Image findImage(Matcher matcher){
    return service.getImage(matcher.group(2));
  }

  private boolean isAnonymous(Authentication authentication){
    for(GrantedAuthority authority: authentication.getAuthorities()){
      if("ROLE_ANONYMOUS".equals(authority.getAuthority())){
        return true;
      }
    }
    return false;
  }

  private boolean isOwned(Image image, Authentication authentication){
    Long id = (Long) authentication.getPrincipal();
    return id.equals(image.getUser().getId());
  }
}
