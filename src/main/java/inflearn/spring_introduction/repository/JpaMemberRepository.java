package inflearn.spring_introduction.repository;

import inflearn.spring_introduction.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from member m where m.name = :name")
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByPhone(String phone) {
        List<Member> result = em.createQuery("select m from member m where m.phone = :phone")
                .setParameter("phone", phone)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}