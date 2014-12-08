package com.cqupt.dormitory.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cqupt.dormitory.dao.AreaDao;
import com.cqupt.dormitory.dao.BuildingDao;
import com.cqupt.dormitory.dao.FloorDao;
import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;
import com.cqupt.dormitory.model.Floor;
import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.service.AreaBuildingService;
import com.cqupt.dormitory.service.RoomService;
import com.cqupt.dormitory.vo.AreaBuilding;
import com.cqupt.dormitory.vo.AreaBuildingEmpty;
import com.cqupt.dormitory.vo.BuildingEmptyBed;
import com.cqupt.dormitory.vo.ResultMessage;

@Service("areaBuildingService")
public class AreaBuildingServiceImpl implements AreaBuildingService {
	@Resource
	private AreaDao areaDao;
	@Resource
	private BuildingDao buildDao;
	@Resource
	private FloorDao floorDao;
	@Resource
	private RoomDao	roomDao;
	@Resource
	private RoomService roomService;
	
	@Override
	public List<Area> findAllArea() {
		return areaDao.findAllArea();
	}

	@Override
	public List<Building> findBuildByAreaName(String areaName) {
		return buildDao.findBuildByAreaName(areaName);
	}

	@Override
	public List<AreaBuilding> findAllAreaAndBuilding() {
		List<AreaBuilding> list = new ArrayList<AreaBuilding>();
		AreaBuilding areaBuilding = null;
		for(Area a : this.findAllArea()){
			areaBuilding =  new AreaBuilding();
			areaBuilding.setArea(a.getName());
			List<Building> blist = this.findBuildByAreaName(a.getName());
			areaBuilding.setBuildingid(this.convertToStringList(blist));
			list.add(areaBuilding);
		}
		return list;
	}

	/**
	 * convertToStringList 简单的转为一个string的list
	 * @param list
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	private List<String> convertToStringList(List<Building> list){
		List<String> stringL = new ArrayList<String>();
		for(Building b : list){
			stringL.add(b.getBuildingNum());
		}
		return stringL;
	}

	@Override
	public List<String> findBuildingBySex(String sex) {
		List<Building> buildings =  buildDao.findBuildingBySex(sex);
		List<String> build = new ArrayList<String>();
		for(Building b : buildings){
			build.add(b.getBuildingNum());
		}
		return build;
	}

	@Override
	public List<String> findFloorByBuildingNum(String buildingNum) {
		List<Floor> floors =  floorDao.findFloorByBuildingNum(buildingNum);
		List<String> floorString = new ArrayList<String>();
		for(Floor f : floors){
			floorString.add(f.getFloorNum());
		}
		return floorString;
	}

	@Override
	public Map<String, Object> findBuildingAndAreaVoBySex(String sex) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<AreaBuildingEmpty> rows = new ArrayList<AreaBuildingEmpty>();
		List<Building> buildings = buildDao.findBuildingBySex(sex);
		String oldArea = "";
		AreaBuildingEmpty e = null;
		List<BuildingEmptyBed> buildingEmptyBed = new ArrayList<BuildingEmptyBed>();
		for(int i = 0;i<buildings.size();i++){
			Building b = buildings.get(i);
			if(i==0) oldArea = b.getArea().getName();
			
			if(!oldArea.equals(b.getArea().getName()) ){
				e = new AreaBuildingEmpty();
				e.setArea(oldArea);
				e.setBuildingEmptyBeds(buildingEmptyBed);
				rows.add(e);
				
				buildingEmptyBed =  null;
				buildingEmptyBed = new ArrayList<BuildingEmptyBed>();
				
				oldArea = b.getArea().getName();
			}
			
			BuildingEmptyBed be = new BuildingEmptyBed();
			be.setBuildingNum(b.getBuildingNum());
			be.setEmptyBed(buildDao.findBuildingEmptyBed(b.getId()));
			buildingEmptyBed.add(be);
			
			
			if(i+1>=buildings.size() ){
			    e = new AreaBuildingEmpty();
				e.setArea(b.getArea().getName());
				e.setBuildingEmptyBeds(buildingEmptyBed);
				buildingEmptyBed =  null;
				buildingEmptyBed = new ArrayList<BuildingEmptyBed>();
				rows.add(e);
			}
			
		}
		map.put("total", buildings.size());
		map.put("columnMax", rows.size());
		map.put("rows", rows);
		return map;
	}

	@Override
	public int updateBuildingMessage(String buildingNum,String sex, String fee, String cata,String area) {
		
		//先要判断一下有没有人住 再修改
		if(buildDao.isBuildingStayPerson(buildingNum)){
				Building b = new Building();
				b.setBuildingNum(buildingNum);
				b.setSex(sex);
				Area a1 = areaDao.findAreaByAreaName(area);
				b.setArea(a1);
				buildDao.updateBuilding(b);
				List<Floor> floor = floorDao.findFloorByBuildingNum(buildingNum);
				List<Integer> floorsId = new ArrayList<Integer>();
				for(Floor f : floor){
					floorsId.add(f.getId());
				}
				roomDao.updateRoom(floorsId, fee, cata);
				return 1;
		}else{
			if(buildDao.isBuildingSexChange(buildingNum, sex)){
				Building b = new Building();
				b.setBuildingNum(buildingNum);
				Area a1 = areaDao.findAreaByAreaName(area);
				b.setArea(a1);
				buildDao.updateBuilding(b);
				List<Floor> floor = floorDao.findFloorByBuildingNum(buildingNum);
				List<Integer> floorsId = new ArrayList<Integer>();
				for(Floor f : floor){
					floorsId.add(f.getId());
				}
				roomDao.updateRoom(floorsId, fee, null);
				return 2;
			}
		}
		return 0;
	}

	@Override
	public List<Building> findBuildingBySexAndArea(String area, String sex) {
		return buildDao.findBuildingBySexAndArea(area,sex);
	}

	@Override
	public List<String> findBuildingByStudents(List<String> studentNums) {
		return buildDao.findBuildingByStudents(studentNums);
	}

	@Override
	public ResultMessage addBuildingAndFloor(MultipartFile file) {
		int rows = 1;
		ResultMessage rm = new ResultMessage();
		try {
			InputStream fs = file.getInputStream();
			String lastName= null;
			Pattern pat = Pattern.compile("\\.[\\w]+");
	 		Matcher m = pat.matcher(file.getOriginalFilename());
			while(m.find()){
				lastName = m.group();
			}
			Workbook wb = null ;
			if(lastName.equals(".xls")){
				wb = new HSSFWorkbook(fs);
			}else if(lastName.equals(".xlsx")){
				OPCPackage docPackage = OPCPackage.open(fs);
				wb = new XSSFWorkbook(docPackage);
			}else{
				throw  new RuntimeException();
			}
			
			//第一个工作簿
			Sheet sheet1 = wb.getSheetAt(0);
			
			for (int j=1;j<sheet1.getLastRowNum()+1;j++) {
				Row row = sheet1.getRow(j);
				
				rows++;
				int totalRoom = -1;
				Area a = new Area();
				Building b = new Building();
				Floor f = new Floor();
				Room r = new Room();
				
				for (Cell cell : row) {
					CellReference cellRef = new CellReference(row.getRowNum(),cell.getColumnIndex());
					//只能用上固定的格式
					switch(cellRef.getCol()){
					case 0:a.setName(cell.toString());break;
					case 1:b.setBuildingNum(String.valueOf(Math.round(Double.parseDouble(cell.toString()))));break;
					case 2:f.setFloorNum(String.valueOf(Math.round(Double.parseDouble(cell.toString()))));break;
					case 3:{
								try {
									totalRoom = Integer.parseInt(cell.toString());
								} catch (Exception e) {
									totalRoom = (int)Double.parseDouble(cell.toString());
								}
								break;
							}
					case 4:r.setTotalBeds(cell.toString());break;
					case 5:r.setCosts(cell.toString());break;
					case 6:b.setSex(cell.toString());break;
					}
				}
				
				Area a2 = areaDao.addArea(a);
				b.setArea(a2);
				Building b2 = buildDao.addBuilding(b);
				
				if(!floorDao.isFloorExist(f.getFloorNum(), b.getBuildingNum())){
					f.setBuilding(b2);
					floorDao.addFloor(f);
				}
				for(int i = 0;i<totalRoom;i++){
					if(!roomService.addRoom(b.getBuildingNum(), f.getFloorNum(), r.getTotalBed(), r.getCost())){
						throw new RuntimeException();
					}
				}
			}
			
			rm.setStatus(1);
			rm.setInfo("成功");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch(NumberFormatException e){
			e.printStackTrace();
			rm.setStatus(0);
			rm.setError("第"+rows+"行出了问题,可能输入有误请检查");
			throw new RuntimeException();
		} catch(Exception e){
			e.printStackTrace();
			rm.setStatus(0);
			rm.setError("第"+rows+"行出了问题,可能输入有误请检查");
			throw new RuntimeException();
		} finally{
			return rm;
		}
		
	}

	@Override
	public boolean deleteBuilding(String building) {
		try {
			if(buildDao.isBuildingStayPerson(building)){
				buildDao.deleteBuilding(building);
			}else {
				//有人 不能删除
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
