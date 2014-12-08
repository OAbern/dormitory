// JavaScript Document

var buildingid="";
var susheid="";
var floorid="";
var action="";
var contains="";
var person="";
var stuName="";
var stuSex="";
var stuNum="";
var area="";
var sum_fenpei=0;
var sum_diaohuan=0;
var sum_newstu_kong=0;
var sum_dhstu_kong=0;
var stuNums=[];
//寝室调换
var diaohuan={};//存放级联
diaohuan['diaohuan_major']=[];
diaohuan['diaohuan_classNum']=[];
var diaohuan_sure={"academy": "","education":"","grade":"","major": "","classNum" : "","sex" : ""};//自己保存
//寝室调换
//新生寝室分配
var fenpei={};//存放级联
fenpei['fenpei_major']=[];//存放动态加载的专业
fenpei['fenpei_classNum']=[];
var fenpei_sure={"academy" : "","education":"","grade":"","major" : "","classNum" : "","sex" : ""};//存放选择新生分配的条件
//新生寝室分配