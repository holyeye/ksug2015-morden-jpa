package hellojpa.entity;

import java.util.List;

public class Ex08JpqlTest extends AbstractEx08Test {
	
	@Override
	public void doTestSimpleQuery() {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				return em.createQuery("select m from Member m where m.age > 18", Member.class)
						.getResultList();
			}
		});
	}

	@Override
	public void doTestJoinQuery() {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				return em.createQuery("select m from Member m join m.team t where t.name = 'teamA'", Member.class)
						.getResultList();
			}
		});
	}

	@Override
	public void doTestFetchJoin() {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				return em.createQuery("select m from Member m join fetch m.team t where t.name = 'teamA'", Member.class)
						.getResultList();
			}
		});
	}
	
	@Override
	public void doTestPagingQuery() {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				return em.createQuery("select m from Member m order by m.age desc", Member.class)
		                .setFirstResult(1)
		                .setMaxResults(1)
		                .getResultList();
			}
		});
	}
}
