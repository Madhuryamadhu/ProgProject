package com.madhudevs.javaprogproj.adminPages;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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
import com.madhudevs.javaprogproj.hibernateBeans.ProgramBean;
import com.madhudevs.javaprogproj.userSection.UserPagesBean;

public class AdminPageDAO {
	private static final Logger logger = LogManager.getLogger();
	
	public int getProgramCount(AdminPagesBean bean) {
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
			logger.info(predicates.size());
			
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
	
	public boolean getProgramsForAdmin(AdminPagesBean bean) {
		List<LinkedList<String>> programsList=null;
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
			
			int totalProgCount=getProgramCount(bean);
			int totalCountOfPagesRequired=(totalProgCount+bean.getPerPageCount()-1)/bean.getPerPageCount();
			bean.setTotalCountofPages(totalCountOfPagesRequired);
		    if (totalProgCount>0) {
		    	programsList=new LinkedList<LinkedList<String>>();
		    	LinkedList<String> programHeaderList=new LinkedList<>();
		    	programHeaderList.add("ID");
		    	programHeaderList.add("Program Name");
		    	programHeaderList.add("Type");
		    	programHeaderList.add("Like Count");
		    	programHeaderList.add("Dislike Count");
		    	programHeaderList.add("Is Hidden");
		    	programHeaderList.add("Modify");
		    	programHeaderList.add("Delete");
		    	programHeaderList.add("Hide");
				programsList.add(programHeaderList);
		    	
				factory = HibernateUtil.getSessionFactory();
				session = factory.getCurrentSession();
				tr = session.beginTransaction();
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);
				Root<ProgramBean> root = cr.from(ProgramBean.class);
				cr = cr.multiselect(root.get("progId"), root.get("progName"), root.get("type"), 
						root.get("lCount"), root.get("dlCount"),root.get("isHidden"));
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (bean.getType() > 0) {
					predicates.add(cb.equal(root.get("type"), bean.getType()));
				}
				if (bean.getComplexity() > 0) {
					predicates.add(cb.equal(root.get("complexity"), bean.getComplexity()));
				}
				logger.info(predicates.size());
				if (predicates.size() > 0) {
					cr = cr.where(predicates.toArray(new Predicate[] {}));
				}
				//for pagination
				Query<Object[]> q = session.createQuery(cr).setFirstResult(bean.getPageNumber())
						.setMaxResults(bean.getPerPageCount());
				List<Object[]> list = q.getResultList();
				
				//programsList=new LinkedList<>();
				for (Object[] objects : list) {
					LinkedList<String> programList=new LinkedList<>();
					programList.add(objects[0]+"");
					programList.add(objects[1]+"");
					programList.add(objects[2]+"");
					programList.add(objects[3]+"");
					programList.add(objects[4]+"");
					String isHidden=objects[5]+"";
					if(isHidden.equals("1")) {
						programList.add("YES");
					}else{
						programList.add("NO");
					}
					programList.add("BUTTON^1^loadModifyProgramPage('"+objects[0]+"')");
					programList.add("BUTTON^2^deleteProgram('"+objects[0]+"')");
					programList.add("BUTTON^3^hideProgram('"+objects[0]+"','"+objects[5]+"')");
					programsList.add(programList);
				}
				bean.setProgramList(programsList);
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
	
	public boolean createProgram(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
			bean.setProg1(bean.getProg1().replaceAll("(\r\n|\n)", "<br/>").replaceAll("\\s", "&nbsp;"));
			bean.setOutput1(bean.getOutput1().replaceAll("(\r\n|\n)", "<br/>").replaceAll("\\s", "&nbsp;"));
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();
			ProgramBean programBean=new ProgramBean();
			programBean.setProgName(bean.getProgName());
			programBean.setType(bean.getType());
			programBean.setIsHidden(bean.getIsHidden());
			programBean.setComplexity(bean.getComplexity());
			programBean.setProg1(bean.getProg1().getBytes());
			programBean.setOutput1(bean.getOutput1().getBytes());
			programBean.setCreateDate(new Date());
			programBean.setCreateUser("Admin");
			programBean.setIsTested(1);
			
			session.save(programBean);
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
	
	public static void main(String[] args) {
		 String str = "This is a string.    This is a long string.";
	        str = str.replaceAll("\\s", "&nbsp;");
	        System.out.println(str);
	}

	public boolean loadPagramDetailsForModify(AdminPagesBean bean) {
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
			cr=cr.multiselect(root.get("type"),root.get("progName"),root.get("isHidden"),root.get("complexity"),root.get("prog1"),root.get("output1"));
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if(bean.getProgId()>0) {
				predicates.add(cb.equal(root.get("progId"), bean.getProgId()));
			}
			logger.info(predicates.size());
			
			if(predicates.size()>0) {
				cr=cr.where(predicates.toArray(new Predicate[]{}));
			}
			 Query<Object[]> q=session.createQuery(cr);
	         List<Object[]> list=q.getResultList();

	         programMap=new HashMap<>();
	         for (Object[] objects : list) {
	        	 bean.setType((Integer)objects[0]);
	        	 bean.setProgName((String)objects[1]);
	        	 bean.setIsHidden((Integer)objects[2]);
	        	 bean.setComplexity((Integer)objects[3]);
	        	 bean.setProg1(new String((byte[])objects[4]).replaceAll("<br>", "").replaceAll("<br/>", "\n").replaceAll("&nbsp;", " "));
	        	 bean.setOutput1(new String((byte[])objects[5]).replaceAll("<br>", "").replaceAll("<br/>", "\n").replaceAll("&nbsp;", " "));
	         }
			
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
	
	
	public boolean modifyProgram(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
			bean.setProg1(bean.getProg1().replaceAll("(\r\n|\n)", "<br/>").replaceAll("\\s", "&nbsp;"));
			bean.setOutput1(bean.getOutput1().replaceAll("(\r\n|\n)", "<br/>").replaceAll("\\s", "&nbsp;"));
			
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<ProgramBean> cr = cb.createCriteriaUpdate(ProgramBean.class);

			Root<ProgramBean> root = cr.from(ProgramBean.class);
			cr.set("type", bean.getType());
			cr.set("progName", bean.getProgName());
			cr.set("isHidden", bean.getIsHidden());
			cr.set("complexity", bean.getComplexity());
			cr.set("prog1", bean.getProg1().getBytes());
			cr.set("output1", bean.getOutput1().getBytes());
			
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
	
	public boolean deleteProgram(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaDelete<ProgramBean> cr = cb.createCriteriaDelete(ProgramBean.class);

			Root<ProgramBean> root = cr.from(ProgramBean.class);
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
	
	public boolean hideShowProgram(AdminPagesBean bean) {
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
			cr.set("isHidden", bean.getIsHidden());
			
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
