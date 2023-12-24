package ManishLokesh.Neptune.v2.Menu.Repository;

import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetMenuRepo extends JpaRepository<Menu,Long> {
    List<Menu> findByOutletId(String outletId);

}
