//package com.typemoon.handler;
//
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.AuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.function.Supplier;
//
//@Component
//public class CustomAuthorizationManagerImpl implements AuthorizationManager<RequestAuthorizationContext> {
//
//    @Override
//    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
//        List<String> permissionList = authentication.get().getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//
//        // Example: Check if the user has a required permission (this logic can be customized)
//        for (ConfigAttribute item : context.getAttributes()) {
//            if (permissionList.contains(item.getAttribute())) {
//                return new AuthorizationDecision(true);
//            }
//        }
//        return new AuthorizationDecision(false);
//    }
//}
