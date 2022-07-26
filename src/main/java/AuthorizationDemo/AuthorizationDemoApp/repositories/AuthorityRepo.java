package AuthorizationDemo.AuthorizationDemoApp.repositories;

import AuthorizationDemo.AuthorizationExampleApp.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {

}
