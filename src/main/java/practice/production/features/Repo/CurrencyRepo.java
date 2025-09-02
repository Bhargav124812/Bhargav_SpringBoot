package practice.production.features.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.production.features.entity.CurrencyEntity;

@Repository
public interface CurrencyRepo extends JpaRepository<CurrencyEntity,Long> {
}
