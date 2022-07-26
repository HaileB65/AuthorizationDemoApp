package AuthorizationDemo.AuthorizationDemoApp;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthorityEnum authority;

    public String getAuthority() {
        //this method needs to return a string as per the GrantedAuthority interface.
        //therefore name() is called on the enumeration.
        return authority.name();
    }

    public enum AuthorityEnum {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_SUPERU,
        UPDATER
    }
}
