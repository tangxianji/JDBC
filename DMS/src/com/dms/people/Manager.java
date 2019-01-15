package com.dms.people;

import java.sql.SQLException;
import java.util.Vector;

import com.dms.information.UpdatePasswordInfo;
import com.dms.information.UpdatePersonInfo;
import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.PasswordNotMatchException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.myexceptioin.UpdateDataException;
import com.dms.myexceptioin.UpdateSuccessException;
import com.dms.queryresultset.AllDormitoryInfo;
import com.dms.queryresultset.AllMaintanceRecord;
import com.dms.queryresultset.DormitoryBuildingInfo;
import com.dms.queryresultset.ManagerInfo;

/*宿舍管理员类*/
public class Manager extends Person {

	private String DormId;
	public String getDormId() {
		return DormId;
	}

	public void setDormId(String dormId) {
		DormId = dormId;
	}


	static ManagerService managerService ;
	
	static{
		managerService = new ManagerServiceImpl();
	}
	
	public Manager() {
		
	}
	
	public Manager(String id) throws DataBaseException, QueryResultIsNullException {
		this.setId(id);
		initPersonalInfo();
	}
	
	@Override
	public void Login() throws PasswordNotMatchException {
		// TODO 自动生成的方法存根
	//managerService.getAllStudentInfo();

	}

	@Override
	public Object PersonalInfo() {
		ManagerInfo  myInfo = new ManagerInfo();
		myInfo.setId(this.getId());
		myInfo.setSex(this.getSex());
		myInfo.setPhone(this.getPhone());
		myInfo.setName(this.getName());
		//myInfo.setHome(this.getHome());   ///
		return myInfo;
	}

	
	@Override
	public void PasswordUpdate(UpdatePasswordInfo updatePasswordInfo) throws PasswordNotMatchException, DataBaseException, UpdateSuccessException {
		updatePasswordInfo.setOwnerId(this.getId());
		managerService.PasswordUpdate(updatePasswordInfo);
	}

	@Override
	public void UpdatePersonalInfo(UpdatePersonInfo updatePersonInfo) throws UpdateDataException, DataBaseException {
		updatePersonInfo.setOwnerId(getId());
		managerService.UpdatePersonalInfo(updatePersonInfo);
		try {
			initPersonalInfo();
		} catch (QueryResultIsNullException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 得到所有学生的信息
	 * @param dmid 
	 * @return
	 * @throws QueryResultIsNullException
	 * @throws DataBaseException 
	 */
	public Vector<Object> getAllStudentInfo(String dmid) throws QueryResultIsNullException, DataBaseException{
		return  (Vector<Object>) managerService.getAllStudentInfo(dmid);
	}
	
	public DormitoryBuildingInfo getDormitory() throws DataBaseException, QueryResultIsNullException{
		DormitoryBuildingInfo dormitoryBuildingInfo =null;
		dormitoryBuildingInfo = managerService.getDormitory(this.getId());
		return dormitoryBuildingInfo;
	}
	/**
	 * 得到含有所有宿舍信息的对象
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public  AllDormitoryInfo getAllDormitoryInfo() throws DataBaseException, QueryResultIsNullException{
		AllDormitoryInfo allDormitoryInfo =null;
		 allDormitoryInfo = managerService.getAllDormitoryInfo(this.getDormId());
		 return allDormitoryInfo;
	}
	
	
	public AllMaintanceRecord getAllMaintanceRecord() throws DataBaseException, QueryResultIsNullException{
		AllMaintanceRecord allMaintanceRecord = null;
		allMaintanceRecord = managerService.getAllMaintenanceRecord(getDormId());
		return allMaintanceRecord;
	}
	
	
	private void initPersonalInfo() throws DataBaseException, QueryResultIsNullException{
		managerService.initPersonalInfo(this);
	}
	/**
	 * 得到所有快递信息
	 * @return
	 * @throws QueryResultIsNullException 
	 * @throws DataBaseException 
	 */
	public Vector<Object> getAllExpressInfo(String dmid) throws DataBaseException, QueryResultIsNullException {
			Vector<Object> vector = managerService.getAllExpressInfo(dmid);
		return vector;
		
	}
	
	public boolean updateExpressTransceiver(int Record, String Data){
		return managerService.updateExpressTransceiver(Record, Data);
	}

	/**
	 * 得到所有夜归记录
	 * @return
	 * @throws DataBaseException
	 * @throws QueryResultIsNullException
	 */
	public Vector<Object> getAllCurfewRecord() throws DataBaseException, QueryResultIsNullException{
		Vector<Object> vector = managerService.getAllCurfewRecord(getDormId());
		return vector;
	}

	public boolean insertRecord(String cmid, String stuId, String data,	String mark) {
		// TODO Auto-generated method stub
		return managerService.insetRecord(cmid, stuId, data, mark);
	}

	public boolean insertStu(String dormId2, String roomId, String bedId, String stuId, String data, String rmark) throws DataBaseException, QueryResultIsNullException{
		// TODO Auto-generated method stub
		return managerService.insertStu(dormId2, roomId, bedId, stuId, data, rmark);
	}

	public boolean insertCurfew(String stuId, String stuName, String dormId2, String roomId, String bedId,
			String backTime, int backSum, String dMId, String reason) throws DataBaseException, QueryResultIsNullException, SQLException {
		// TODO Auto-generated method stub
		return managerService.insertCurfew(stuId, stuName, dormId2,roomId,bedId,backTime, backSum, dMId,reason);
	}

	public boolean insertLCRecord(String stuId, String stuName, String data, String lC, String mark) throws DataBaseException, QueryResultIsNullException{
		// TODO Auto-generated method stub
		return managerService.insertLCRecord(stuId,  stuName,  data,  lC,  mark);
	}

	public boolean outStu(String stuId, String rmark) throws DataBaseException {
		// TODO Auto-generated method stub
		return managerService.outStu(stuId, rmark);
	}

	public String getStuName(String stuId) throws DataBaseException {
		// TODO Auto-generated method stub
		return  managerService.getStuName(stuId);
	}

}
