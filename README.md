# crm_ssh
学习SSH框架后，练手的客户关系管理系统。hibernate5.2.x+struts2.3.x+spring4.3+Mysql5。主要实现了客户基本信息、联系人信息以及客户拜访记录的增删改查。通过hibernate框架对客户与联系人之间一对多关系、客户与拜访记录以及联系人与拜访记录的一对多关系的配置。
dao层只使用了一次BaseDao抽取，运用在一个dao类上
