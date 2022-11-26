package com.example.portfolio.security;

import com.example.portfolio.domain.portfolio.Portfolio;
import com.example.portfolio.error.NotFoundException;
import com.example.portfolio.service.portfolio.PortfolioService;
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
public class PortfolioVoter implements AccessDecisionVoter<FilterInvocation> {

  private static final Pattern PATTERN = Pattern.compile("/api/(v.+)/portfolios/(\\d+)");
  private final PortfolioService service;

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

    if (!checkAdequateSubject(matcher)) return ACCESS_ABSTAIN;

    try {
      Portfolio portfolio = findPortfolio(matcher);
      if ("GET".equals(method)) {
        if (portfolio.isPublic()) return ACCESS_GRANTED;
        if (isAnonymous(authentication)) return ACCESS_DENIED;
        if (isNotOwned(portfolio, authentication)) return ACCESS_DENIED;
      }
      if (
        "POST".equals(method) ||
        "PUT".equals(method) ||
        "PATCH".equals(method) ||
        "DELETE".equals(method)
      ) {
        if (isAnonymous(authentication)) return ACCESS_DENIED;
        if (isNotOwned(portfolio, authentication)) return ACCESS_DENIED;
      }
      return ACCESS_DENIED;
    } catch (NotFoundException e) {
      return ACCESS_DENIED;
    }
  }

  private boolean checkAdequateSubject(Matcher matcher) {
    return matcher.matches();
  }

  private Portfolio findPortfolio(Matcher matcher) {
    return service.getPortfolio(Long.parseLong(matcher.group(2)));
  }

  private boolean isAnonymous(Authentication authentication) {
    for (GrantedAuthority authority : authentication.getAuthorities()) {
      if ("ROLE_ANONYMOUS".equals(authority.getAuthority())) {
        return true;
      }
    }
    return false;
  }

  private boolean isNotOwned(Portfolio portfolio, Authentication authentication) {
    Long id = (Long) authentication.getPrincipal();
    return !id.equals(portfolio.getUser().getId());
  }

}
