package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 어플 전체에서 공유
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");
        // 엔티티 매니저는 쓰레드간에 공유X(사용하고 버려야 함)
        EntityManager em = enf.createEntityManager();
        // ** JPA의 모든 데이터 변경은 트랜잭션 안에서 실행 **
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // insert
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);
            // 조회
//            Member findMember = em.find(Member.class, 1L);
            // 삭제
//            em.remove(findMember);
            // 수정(commit 안해줘도 됨)
//            findMember.setName("HelloJPA");

//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // 전체 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            // 페이지네이션
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//            .setFirstResult(5).setMaxResults(8).getResultList();
//            for(Member member : result){
//                System.out.println("member.name : "+member.getName());
//            }

//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L,"B");
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");
            // 변경감지
            // 수정후에 persist 안해도 db에 수정 사항이 저장됨
//            em.persist(member1);
//            em.persist(member2);
            // persist 시에는 1차캐시에 저장 및 쓰기지연 저장소에 sql문 저장

            // commit시에 쓰기 지연 저장소에 있는 sql문 db에 보냄
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        enf.close();
    }
}
