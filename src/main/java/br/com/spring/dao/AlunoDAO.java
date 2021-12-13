package br.com.spring.dao;

import br.com.spring.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoDAO extends JpaRepository<Aluno, Long> {

    @Query("SELECT a FROM Aluno a WHERE a.status = 'ATIVO'")
    public List<Aluno> findByStatusAtivo();

    @Query("SELECT a FROM Aluno a WHERE a.status = 'INATIVO'")
    public List<Aluno> findByStatusInativo();

    @Query("SELECT a FROM Aluno a WHERE a.status = 'CANCELADO'")
    public List<Aluno> findByStatusCancelado();

    @Query("SELECT a FROM Aluno a WHERE a.status = 'TRANCADO'")
    public List<Aluno> findByStatusTrancado();

    /*!= JPQL*/
    public List<Aluno> findByNomeContainingIgnoreCase(String nome);
    // ends here...
}
