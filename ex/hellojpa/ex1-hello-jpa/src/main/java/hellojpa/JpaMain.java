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
        EntityManager en = enf.createEntityManager();
        // ** JPA의 모든 데이터 변경은 트랜잭션 안에서 실행 **
        EntityTransaction tx = en.getTransaction();
        tx.begin();
        try {
            // insert
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            en.persist(member);
            // 조회
//            Member findMember = en.find(Member.class, 1L);
            // 삭제
//            en.remove(findMember);
            // 수정(commit 안해줘도 됨)
//            findMember.setName("HelloJPA");

//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // 전체 조회
            List<Member> result = en.createQuery("select m from Member as m", Member.class).getResultList();
            // 페이지네이션
//            List<Member> result = en.createQuery("select m from Member as m", Member.class)
//            .setFirstResult(5).setMaxResults(8).getResultList();
            for(Member member : result){
                System.out.println("member.name : "+member.getName());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            en.close();
        }
        enf.close();
    }
}
