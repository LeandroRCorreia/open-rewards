package com.rewards.openrewards.modules.auth.business.enums;


import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String role;

    UserRoles(String role){
        this.role = role;
    }


    public static UserRoles fromString(String text) {
        for (UserRoles b : UserRoles.values()) {
            if (b.role.equalsIgnoreCase(text) || b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Nenhuma role encontrada para o valor: " + text);
    }

}
