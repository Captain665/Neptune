package ManishLokesh.Neptune.v1.OutletsAndMenu.Repository;

import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OutletRepo extends JpaRepository<Outlet, Long> {
     Outlet saveAndFlush(Outlet outlet);
     Optional<Outlet> findById(Long Id);

    List<Outlet> findByStationCode(String stationCode);
    @Query(value = "SELECT * from Outlets where id = :id", nativeQuery = true)
    Outlet findByOutletId(@Param("id") Long id);

}
