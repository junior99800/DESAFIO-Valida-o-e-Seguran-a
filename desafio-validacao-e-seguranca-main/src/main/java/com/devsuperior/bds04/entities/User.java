    package com.devsuperior.bds04.entities;


    import jakarta.persistence.*;
    import lombok.*;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;


    import java.io.Serializable;
    import java.util.Collection;
    import java.util.HashSet;
    import java.util.Set;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode(of = {"id"})
    @Entity
    @Table(name = "tb_user")
    public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String email;
        private String password;

        @Setter(AccessLevel.NONE)
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "tb_user_role",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public void addRole(Role role) {
            roles.add(role);
        }

        public boolean hasRole(String roleName){
            for(Role role : roles){
                if (role.getAuthority().equals(roleName)){
                    return true;
                }
            }
            return false;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return roles;
        }

        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
