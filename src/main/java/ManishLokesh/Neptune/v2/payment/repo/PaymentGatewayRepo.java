package ManishLokesh.Neptune.v2.payment.repo;import ManishLokesh.Neptune.v2.payment.PaymentGateways;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;import java.util.List;@Repositorypublic interface PaymentGatewayRepo extends JpaRepository<PaymentGateways, Long> {    public List<PaymentGateways> findAll();}