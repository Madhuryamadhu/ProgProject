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
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.madhudevs.common.CommonFunctions;
import com.madhudevs.common.HibernateUtil;
import com.madhudevs.javaprogproj.hibernateBeans.AdminBean;
import com.madhudevs.javaprogproj.hibernateBeans.ProgramBean;
import com.madhudevs.javaprogproj.hibernateBeans.TypeBean;
import com.madhudevs.javaprogproj.userSection.UserPagesBean;

public class AdminPageDAO {
	private static final Logger logger = LogManager.getLogger();
	
	
	
	public int adminLogin(AdminPagesBean bean,HttpSession httpsession) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		int loginResult=-1;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

			Root<AdminBean> root = cr.from(AdminBean.class);
			cr=cr.multiselect(root.get("adminId"),root.get("adminUsername"),root.get("isActive"),root.get("level"));
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			predicates.add(cb.equal(root.get("adminUsername"), bean.getUsername()));
			predicates.add(cb.equal(root.get("adminPassword"), bean.getPassword()));
			cr=cr.where(predicates.toArray(new Predicate[]{}));
			
			Query<Object[]> q=session.createQuery(cr);
	        List<Object[]> list=q.getResultList();

	        //loginResult [1:username and password wrong] ,[2:admin is inactive], [0:login success]
	        if(list.size()>0) {
	        	for (Object[] objects : list) {
	        		int isActive=(Integer)objects[2];
		        	if(isActive==1) {
		        		loginResult=0;
		        		httpsession.setAttribute("admin_Id", objects[0]+"");
		        		httpsession.setAttribute("admin_username", objects[1]+"");
		        		httpsession.setAttribute("admin_isActive", objects[2]+"");
		        		httpsession.setAttribute("admin_level",objects[3]+"");
		    			logger.info("Login Successfull");
		        	}else {
		        		logger.info("Login Failed admin is inactive.");
		        		loginResult=2;
		        	}
		         }
	        }else {
	        	logger.info("Login Failed Wrong username or password");
	        	loginResult=1;
	        }
	         
			
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
		return loginResult;
	}
	
	
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
		    	LinkedList<String> programHeaderList=new LinkedList<String>();
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
				
				for (Object[] objects : list) {
					LinkedList<String> programList=new LinkedList<String>();
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

	
	public int getTypesCount(AdminPagesBean bean) {
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

			Root<TypeBean> root = cr.from(TypeBean.class);
			cr=cr.select(cb.count(root.get("typeId")));
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			/*if(bean.getType()>0) {
				predicates.add(cb.equal(root.get("type"), bean.getType()));
			}
			if(bean.getComplexity()>0) {
				predicates.add(cb.equal(root.get("complexity"), bean.getComplexity()));
			}
			logger.info(predicates.size());
			
			if(predicates.size()>0) {
				cr=cr.where(predicates.toArray(new Predicate[]{}));
			}*/
			
			
			//for pagination
			 q=session.createQuery(cr).getSingleResult();

			 logger.info("TOTAL COUNT>>TYPE::-"+q);
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
	
	
	public boolean getTypesForAdmin(AdminPagesBean bean) {
		List<LinkedList<String>> typesList=null;
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
			
			int totalProgCount=getTypesCount(bean);
			int totalCountOfPagesRequired=(totalProgCount+bean.getPerPageCount()-1)/bean.getPerPageCount();
			bean.setTotalCountofPages(totalCountOfPagesRequired);
		    if (totalProgCount>0) {
		    	typesList=new LinkedList<LinkedList<String>>();
		    	LinkedList<String> typeHeaderList=new LinkedList<String>();
		    	typeHeaderList.add("ID");
		    	typeHeaderList.add("Type Name");
		    	typeHeaderList.add("Is Hidden");
		    	typeHeaderList.add("Modify");
		    	typeHeaderList.add("Delete");
		    	typeHeaderList.add("Hide");
		    	typesList.add(typeHeaderList);
		    	
				factory = HibernateUtil.getSessionFactory();
				session = factory.getCurrentSession();
				tr = session.beginTransaction();
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);
				Root<TypeBean> root = cr.from(TypeBean.class);
				cr = cr.multiselect(root.get("typeId"), root.get("typeName"), root.get("isHidden"));
				
				/*List<Predicate> predicates = new ArrayList<Predicate>();
				if (bean.getType() > 0) {
					predicates.add(cb.equal(root.get("type"), bean.getType()));
				}
				if (bean.getComplexity() > 0) {
					predicates.add(cb.equal(root.get("complexity"), bean.getComplexity()));
				}
				logger.info(predicates.size());
				if (predicates.size() > 0) {
					cr = cr.where(predicates.toArray(new Predicate[] {}));
				}*/
				//for pagination
				Query<Object[]> q = session.createQuery(cr).setFirstResult(bean.getPageNumber())
						.setMaxResults(bean.getPerPageCount());
				List<Object[]> list = q.getResultList();
				
				for (Object[] objects : list) {
					LinkedList<String> typeList=new LinkedList<String>();
					typeList.add(objects[0]+"");
					typeList.add(objects[1]+"");
					String isHidden=objects[2]+"";
					if(isHidden.equals("1")) {
						typeList.add("YES");
					}else{
						typeList.add("NO");
					}
					typeList.add("BUTTON^1^loadModifyTypePage('"+objects[0]+"')");
					typeList.add("BUTTON^2^deleteType('"+objects[0]+"')");
					typeList.add("BUTTON^3^hideType('"+objects[0]+"','"+objects[2]+"')");
					typesList.add(typeList);
				}
				bean.setTypeList(typesList);
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

	public boolean createType(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();
			TypeBean typebean=new TypeBean();
			typebean.setTypeName(bean.getTypeName());
			typebean.setIsHidden(bean.getIsHidden());
			typebean.setCreateDate(new Date());
			typebean.setCreateUser("Admin");
			session.save(typebean);
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

	public boolean loadTypeFOrModify(AdminPagesBean bean) {
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

			Root<TypeBean> root = cr.from(TypeBean.class);
			cr=cr.multiselect(root.get("typeName"),root.get("isHidden"));
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if(bean.getType()>0) {
				predicates.add(cb.equal(root.get("typeId"), bean.getType()));
			}
			
			if(predicates.size()>0) {
				cr=cr.where(predicates.toArray(new Predicate[]{}));
			}
			 Query<Object[]> q=session.createQuery(cr);
	         List<Object[]> list=q.getResultList();

	         for (Object[] objects : list) {
	        	 bean.setTypeName(objects[0]+"");
	        	 bean.setIsHidden((Integer)objects[1]);
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

	public boolean modifyType(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<TypeBean> cr = cb.createCriteriaUpdate(TypeBean.class);

			Root<TypeBean> root = cr.from(TypeBean.class);
			cr.set("typeName", bean.getTypeName());
			cr.set("isHidden", bean.getIsHidden());
			
			cr.where(cb.equal(root.get("typeId"), bean.getType()));
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

	public boolean deleteType(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaDelete<TypeBean> cr = cb.createCriteriaDelete(TypeBean.class);

			Root<TypeBean> root = cr.from(TypeBean.class);
			cr.where(cb.equal(root.get("typeId"), bean.getType()));
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

	public boolean hideShowType(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<TypeBean> cr = cb.createCriteriaUpdate(TypeBean.class);

			Root<TypeBean> root = cr.from(TypeBean.class);
			cr.set("isHidden", bean.getIsHidden());
			
			cr.where(cb.equal(root.get("typeId"), bean.getType()));
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
	
	
	
	public boolean getAdminList(AdminPagesBean bean) {
		List<LinkedList<String>> adminsList=null;
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
			
			int totalProgCount=getAdminsCount(bean);
			int totalCountOfPagesRequired=(totalProgCount+bean.getPerPageCount()-1)/bean.getPerPageCount();
			bean.setTotalCountofPages(totalCountOfPagesRequired);
		    if (totalProgCount>0) {
		    	adminsList=new LinkedList<LinkedList<String>>();
		    	LinkedList<String> typeHeaderList=new LinkedList<String>();
		    	typeHeaderList.add("AdminId");
		    	typeHeaderList.add("Username");
		    	typeHeaderList.add("Password");
		    	typeHeaderList.add("CreateDate");
		    	typeHeaderList.add("CreateUser");
		    	typeHeaderList.add("Is Active");
		    	typeHeaderList.add("Level");
		    	typeHeaderList.add("Deactivate");
		    	typeHeaderList.add("Modify");
		    	typeHeaderList.add("Delete");
		    	adminsList.add(typeHeaderList);
		    	
				factory = HibernateUtil.getSessionFactory();
				session = factory.getCurrentSession();
				tr = session.beginTransaction();
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);
				Root<AdminBean> root = cr.from(AdminBean.class);
				cr = cr.multiselect(root.get("adminId"), root.get("adminUsername"), root.get("adminPassword"), 
						root.get("createdate"), root.get("createUser"), root.get("isActive"), root.get("level"));
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				//predicates.add(cb.equal(root.get("isActive"), 1));
				predicates.add(cb.greaterThan(root.get("level").as(Integer.class), bean.getAdminLevel()));
				
				
				
				if(predicates.size()>0) {
					cr=cr.where(predicates.toArray(new Predicate[]{}));
				}
				
				
				//for pagination
				Query<Object[]> q = session.createQuery(cr).setFirstResult(bean.getPageNumber())
						.setMaxResults(bean.getPerPageCount());
				List<Object[]> list = q.getResultList();
				
				for (Object[] objects : list) {
					LinkedList<String> adminList=new LinkedList<String>();
					adminList.add(objects[0]+"");
					adminList.add(objects[1]+"");
					adminList.add(objects[2]+"");
					adminList.add(new CommonFunctions().getStringdateFromDate((Date)objects[3], "dd-MM-yyyy"));
					adminList.add(objects[4]+"");
					String isActive=objects[5]+"";
					if(isActive.equals("1")) {
						adminList.add("YES");
					}else{
						adminList.add("NO");
					}
					adminList.add(objects[6]+"");
					adminList.add("BUTTON^4^deactivateAdmin('"+objects[0]+"','"+objects[5]+"')");
					adminList.add("BUTTON^2^deleteAdmin('"+objects[0]+"')");
					adminList.add("BUTTON^1^loadModifyAdmin('"+objects[0]+"')");
					adminsList.add(adminList);
				}
				bean.setAdminsList(adminsList);;
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
	
	public int getAdminsCount(AdminPagesBean bean) {
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

			Root<AdminBean> root = cr.from(AdminBean.class);
			cr=cr.select(cb.count(root.get("adminId")));
			List<Predicate> predicates = new ArrayList<Predicate>();
			//predicates.add(cb.equal(root.get("isActive"), 1));
			predicates.add(cb.greaterThan(root.get("level").as(Integer.class), bean.getAdminLevel()));

			if(predicates.size()>0) {
				cr=cr.where(predicates.toArray(new Predicate[]{}));
			}
			//for pagination
			 q=session.createQuery(cr).getSingleResult();

			 logger.info("TOTAL COUNT>>TYPE::-"+q);
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
	
	public boolean createAdmin(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();
			AdminBean adminBean=new AdminBean();
			adminBean.setAdminUsername(bean.getUsername());
			adminBean.setAdminPassword(bean.getPassword());
			adminBean.setLevel(bean.getAdminLevel());
			adminBean.setIsActive(bean.getAdminIsActive());
			adminBean.setCreatedate(new Date());
			adminBean.setCreateUser(bean.getAdminId());
			
			session.save(adminBean);
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
	
	
	
	public boolean deactivateAdmin(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<AdminBean> cr = cb.createCriteriaUpdate(AdminBean.class);
			Root<AdminBean> root = cr.from(AdminBean.class);
			cr.set("isActive", bean.getAdminIsActive());
			cr.where(cb.equal(root.get("adminId"), bean.getAdminId()));
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
	
	
	public boolean deleteAdmin(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaDelete<AdminBean> cr = cb.createCriteriaDelete(AdminBean.class);

			Root<AdminBean> root = cr.from(AdminBean.class);
			cr.where(cb.equal(root.get("adminId"), bean.getAdminId()));
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
	
	public boolean loadAdminForModify(AdminPagesBean bean) {
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

			Root<AdminBean> root = cr.from(AdminBean.class);
			cr=cr.multiselect(root.get("adminUsername"),root.get("adminPassword"),root.get("isActive"),root.get("level"));
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if(bean.getAdminId()>0) {
				predicates.add(cb.equal(root.get("adminId"), bean.getAdminId()));
			}
			
			if(predicates.size()>0) {
				cr=cr.where(predicates.toArray(new Predicate[]{}));
			}
			 Query<Object[]> q=session.createQuery(cr);
	         List<Object[]> list=q.getResultList();

	         for (Object[] objects : list) {
	        	bean.setUsername(objects[0]+"");
	        	bean.setPassword(objects[1]+"");
	        	bean.setAdminIsActive((Integer)objects[2]);
	        	bean.setAdminLevel((Integer)objects[3]);
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
	
	
	public boolean modifyAdmin(AdminPagesBean bean) {
		SessionFactory factory=null;
		Session session=null;
		Transaction tr=null;
		boolean isSuccess=false;
		try {
		    factory = HibernateUtil.getSessionFactory();
			session = factory.getCurrentSession();
			tr = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<AdminBean> cr = cb.createCriteriaUpdate(AdminBean.class);

			Root<AdminBean> root = cr.from(AdminBean.class);
			cr.set("adminUsername", bean.getUsername());
			cr.set("adminPassword", bean.getPassword());
			cr.set("isActive", bean.getAdminIsActive());
			cr.set("level", bean.getAdminLevel());
			
			cr.where(cb.equal(root.get("adminId"), bean.getAdminId()));
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
