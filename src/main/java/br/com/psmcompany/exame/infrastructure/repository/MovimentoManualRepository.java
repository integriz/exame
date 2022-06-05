package br.com.psmcompany.exame.infrastructure.repository;

import br.com.psmcompany.exame.infrastructure.model.MovimentoManualModel;
import br.com.psmcompany.exame.infrastructure.model.pk.MovimentoManualModelPk;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MovimentoManualRepository extends JpaRepository<MovimentoManualModel, MovimentoManualModelPk> {
    Optional<List<MovimentoManualModel>> findByIdDatAnoAndIdDatMes(BigDecimal datAno, BigDecimal datMes);
    Long countByIdDatAnoAndIdDatMes(BigDecimal datAno, BigDecimal datMes);
}
