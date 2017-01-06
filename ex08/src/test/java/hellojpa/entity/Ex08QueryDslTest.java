package hellojpa.entity;

import java.util.List;

import org.junit.Test;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

public class Ex08QueryDslTest extends AbstractEx08Test {
	
	QMember m;
	QTeam t;
	
	JPAQuery<Member> query;
	
	@Override
	public void postSetup() {
		super.postSetup();
		
		m = QMember.member;
		t = QTeam.team;
		query = new JPAQuery<Member>(em);
	}
	
	@Override
	public void doTestSimpleQuery() {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				return query.from(m)
		                .where(m.age.gt(18))
		                .fetch();
			}
		});
	}

	@Override
	public void doTestJoinQuery() {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				return query.select(m).from(m)
		                .join(m.team, t)
		                .where(t.name.eq("teamA"))
		                .fetch();
			}
		});
	}

	@Override
	public void doTestFetchJoin() {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				return query.from(m).join(m.team, t).fetchJoin().fetch();
			}
		});
	}
	
	@Override
	public void doTestPagingQuery() {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				return query.from(m)
		        		.orderBy(m.age.desc())
		        		.limit(1)
		                .offset(1)
		                .fetch();
			}
		});
	}
	
	@Test
	public void testDynamicQuery() throws Exception {
		doLogic(new LogicExecutor() {
			@Override
			public List<Member> execute() {
				
		        String name = "member";
		        int age = 9;

		        BooleanBuilder builder = new BooleanBuilder();
		        if (name != null) {
		            builder.and(m.name.contains(name));
		        }
		        if (age != 0) {
		            builder.and(m.age.gt(age));
		        }
		        
				return query.from(m)
		                .where(builder)
		                .fetch();
			}
		});
	}
}
