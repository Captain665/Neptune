package ManishLokesh.Neptune.v2.Outlets.Repository;

import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GetOutletRepo extends JpaRepository<Outlet, Long> {
    List<Outlet> findByStationCode(String stationCode);
    @Override
    Optional<Outlet> findById(Long aLong);
}

