package ManishLokesh.Neptune.v1.OutletsAndMenu.Repository;

import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
    Menu saveAndFlush(Menu menu);
    Optional<Menu> findById(Long outletId);
    List<Menu> findByOutletId(String outletId);

}
