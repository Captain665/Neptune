package ManishLokesh.Neptune.v2.Outlets.Repository;

import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GetOutletRepo extends JpaRepository<Outlet, Long> {
    @Query(value = "SELECT * from outlets where station_code LIKE %:stationCode%", nativeQuery = true)
    List<Outlet> findByStationCode(@Param("stationCode") String stationCode);

    @Override
    Optional<Outlet> findById(Long aLong);
}

