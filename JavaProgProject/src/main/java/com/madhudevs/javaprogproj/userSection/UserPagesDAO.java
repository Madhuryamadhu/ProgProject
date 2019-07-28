package com.madhudevs.javaprogproj.userSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.madhudevs.common.HibernateUtil;
import com.madhudevs.javaprogproj.hibernateBeans.ComplexityBean;
import com.madhudevs.javaprogproj.hibernateBeans.ProgramBean;
import com.madhudevs.javaprogproj.hibernateBeans.TypeBean;

public class UserPagesDAO {
	private static final Logger logger = LogManager.getLogger();
	
	
	public boolean getPagramForView(UserPagesBean bean) {
		HashMap<Integer, String> programMap=null;
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

			Root<ProgramBean> root = cr.from(ProgramBean.class);
			cr=cr.multiselect(root.get("progId"),root.get("progName"),root.get("prog1"),root.get("output1"),root.get("lCount"),root.get("dlCount"));
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if(bean.getProgId()>0) {
				predicates.add(cb.equal(root.get("progId"), bean.getProgId()));
			}
			
			if(predicates.size()>0) {
				cr=cr.where(predicates.toArray(new Predicate[]{}));
			}
			
			 Query<Object[]> q=session.createQuery(cr);
	         List<Object[]> list=q.getResultList();

	         programMap=new HashMap<Integer, String>();
	         for (Object[] objects : list) {
	        	 bean.setProgId((Integer)objects[0]);
	        	 bean.setProgName((String)objects[1]);
	        	 bean.setProgram1(new String((byte[])objects[2]));
	        	 bean.setOutput1(new String((byte[])objects[3]));
	        	 bean.setLikeCount((Integer)objects[4]);
	        	 bean.setDislikeCount((Integer)objects[5]);
	         }
	         
			
			//bean.setProgramMap(programMap);
			tr.commit();
			
			isSuccess=true;
		} catch (HibernateException e) {
			tr.rollback();
			logger.error("Exception occured",e);
		} catch (Exception e) {
			tr.rollback();
			logger.error("Exception occured",e);
		}finally {
	        if (session!=null&&session.isOpen()) {
	            session.close();
	        }
	    }
		return isSuccess;
	}
	
	
	public int getProgramCount(UserPagesBean bean) {
		Long q=null;
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Long> cr = cb.createQuery(Long.class);

			Root<ProgramBean> root = cr.from(ProgramBean.class);
			cr=cr.select(cb.count(root.get("progId")));
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if(bean.getType()>0) {
				predicates.add(cb.equal(root.get("type"), bean.getType()));
			}
			if(bean.getComplexity()>0) {
				predicates.add(cb.equal(root.get("complexity"), bean.getComplexity()));
			}
			predicates.add(cb.equal(root.get("isHidden"), 0));
			predicates.add(cb.equal(root.get("isTested"), 1));
			
			if(predicates.size()>0) {
				cr=cr.where(predicates.toArray(new Predicate[]{}));
			}
			
			
			//for pagination
			 q=session.createQuery(cr).getSingleResult();

			 logger.info("TOTAL COUNT>>"+q);
			tr.commit();
			
		} catch (HibernateException e) {
			tr.rollback();
			logger.error("Exception occured",e);
		} catch (Exception e) {
			tr.rollback();
			logger.error("Exception occured",e);
		}finally {
	        if (session!=null&&session.isOpen()) {
	            session.close();
	        }
	    }
		return q.intValue();
	
	}
	
	public boolean getProgramList(UserPagesBean bean) {
		HashMap<Integer, String> programMap=null;
		HashMap<Integer, String> likeCountMap=null;
		HashMap<Integer, String> dislikeCountMap=null;
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
			
			int totalProgCount=getProgramCount(bean);
			int totalCountOfPagesRequired=(totalProgCount+bean.getPerPageCount()-1)/bean.getPerPageCount();
			bean.setTotalCountofPages(totalCountOfPagesRequired);
		    if (totalProgCount>0) {
				factory = HibernateUtil.getSessionFactory();
				session = factory.getCurrentSession();
				tr = session.beginTransaction();
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);
				Root<ProgramBean> root = cr.from(ProgramBean.class);
				cr = cr.multiselect(root.get("progId"), root.get("progName"), root.get("lCount"), root.get("dlCount"));
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (bean.getType() > 0) {
					predicates.add(cb.equal(root.get("type"), bean.getType()));
				}
				if (bean.getComplexity() > 0) {
					predicates.add(cb.equal(root.get("complexity"), bean.getComplexity()));
				}
				predicates.add(cb.equal(root.get("isHidden"), 0));
				predicates.add(cb.equal(root.get("isTested"), 1));
				
				if (predicates.size() > 0) {
					cr = cr.where(predicates.toArray(new Predicate[] {}));
				}
				//for pagination
				Query<Object[]> q = session.createQuery(cr).setFirstResult(bean.getPageNumber())
						.setMaxResults(bean.getPerPageCount());
				List<Object[]> list = q.getResultList();
				programMap = new HashMap<>();
				likeCountMap = new HashMap<>();
				dislikeCountMap = new HashMap<>();
				for (Object[] objects : list) {
					logger.info((Integer) objects[0] + "  " + (String) objects[1]);
					programMap.put((Integer) objects[0], (String) objects[1]);
					likeCountMap.put((Integer) objects[0], (Integer) objects[2] + "");
					dislikeCountMap.put((Integer) objects[0], (Integer) objects[3] + "");
				}
				bean.setProgramMap(programMap);
				bean.setLikeCountMap(likeCountMap);
				bean.setDislikeCountMap(dislikeCountMap);
				tr.commit();
			}else logger.info("No programs available to select!!");
		    
			isSuccess=true;
		} catch (HibernateException e) {
			logger.error("Exception occured",e);
			tr.rollback();
		} catch (Exception e) {
			logger.error("Exception occured",e);
			tr.rollback();
		}finally {
	        if (session!=null&&session.isOpen()) {
	            session.close();
	        }
	    }
		return isSuccess;
	}
	
	
	public boolean getTypes(UserPagesBean bean) {
		HashMap<Integer, String> typeMap=null;
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TypeBean> cr = cb.createQuery(TypeBean.class);

			Root<TypeBean> root = cr.from(TypeBean.class);
			cr.select(root).where(cb.equal(root.get("isHidden"), 0));
			
			
			cr.select(root);
			 
			Query<TypeBean> query = session.createQuery(cr);
			
			List<TypeBean> results = query.getResultList();
			
			typeMap=new HashMap<>();
			for (TypeBean typeBean : results) {
				typeMap.put(typeBean.getTypeId(), typeBean.getTypeName());
			}
			tr.commit();
			
			bean.setTypeMap(typeMap);
			isSuccess=true;
		} catch (HibernateException e) {
			tr.rollback();
			logger.error("Exception occured",e);
		} catch (Exception e) {
			tr.rollback();
			logger.error("Exception occured",e);
		}finally {
	        if (session!=null&&session.isOpen()) {
	            session.close();
	        }
	    }
		return isSuccess;
	}
	
	
	public boolean getComplexityDetails(UserPagesBean bean) {
		HashMap<Integer, String> complexityMap=null;
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<ComplexityBean> cr = cb.createQuery(ComplexityBean.class);
			Root<ComplexityBean> root = cr.from(ComplexityBean.class);
			cr.select(root);
			 
			Query<ComplexityBean> query = session.createQuery(cr);
			List<ComplexityBean> results = query.getResultList();
			complexityMap=new HashMap<>();
			for (ComplexityBean complexityBean : results) {
				complexityMap.put(complexityBean.getComplexityId(), complexityBean.getComplexityName());
			}
			tr.commit();
			
			bean.setComplexityMap(complexityMap);
			isSuccess=true;
		} catch (HibernateException e) {
			tr.rollback();
			logger.error("Exception occured",e);
		} catch (Exception e) {
			tr.rollback();
			logger.error("Exception occured",e);
		}finally {
	        if (session!=null&&session.isOpen()) {
	            session.close();
	        }
	    }
		return isSuccess;
	}
	
	
	public static void main(String[] args) {
		int total=130;
		int perpage=10;
		System.out.println((total+perpage-1)/perpage);
	}


	public boolean updateLikeDislike(UserPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<ProgramBean> cr = cb.createCriteriaUpdate(ProgramBean.class);

			Root<ProgramBean> root = cr.from(ProgramBean.class);
			if(bean.getLikeCount()>0) {
				cr.set("lCount", bean.getLikeCount());
			}
			if(bean.getDislikeCount()>0) {
				cr.set("dlCount", bean.getDislikeCount());
			}
			
			cr.where(cb.equal(root.get("progId"), bean.getProgId()));
			int count=session.createQuery(cr).executeUpdate();
		     tr.commit();
			
		     if(count>0) {
		    	 isSuccess=true;
		     }
			
		} catch (HibernateException e) {
			tr.rollback();
			logger.error("Exception occured",e);
		} catch (Exception e) {
			tr.rollback();
			logger.error("Exception occured",e);
		}finally {
	        if (session!=null&&session.isOpen()) {
	            session.close();
	        }
	    }
		return isSuccess;
	
		
	}
}
