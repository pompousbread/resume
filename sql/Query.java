/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com;


import java.sql.*;

/**
 *
 * @author Ryan Heinrich
 */
public class Query {
    
    public Query(){}
    
    public String getQuery(String sqlStatment, int queryNumber){
        Connection conn = null;
        String returnMessage = ""; 
            try{
                String driverName = "oracle.jdbc.driver.OracleDriver";
                Class.forName(driverName);
                String serverName = "dbsvcs.cs.uno.edu";
                String serverPort = "1521";
                String sid = "orcl";
                String url = "jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
                String userName = "rwheinri";
                String password = "g3tJsNmt";
                conn = DriverManager.getConnection(url, userName, password);
                System.out.println("Sucessfully connected to the database.");
            
            }catch(ClassNotFoundException e){
                System.out.println("Could not find database driver "+e.getMessage());
            }catch(SQLException e){
                System.out.println("Could not connect to database "+e.getMessage());
            }
            try{
                boolean once = false;
                Statement st = conn.createStatement();
                ResultSet rs =st.executeQuery(sqlStatment);
                //ResultSet rs =st.executeQuery("Select * from person");
                while(rs.next()){
                    if(queryNumber == 1){
                        if(once == false){
                            returnMessage += String.format("%-26s\n", "p_name");
                            once = true;}
                        String name = rs.getString(1).toString();
                        returnMessage += ""+name+" "+ System.lineSeparator();
                    }else if(queryNumber == 2){
                        if(once == false){
                            returnMessage += String.format("%-20s%10s%10s\n", "p_name", "pay_rate"," pay_type");
                            once = true;}
                        int no = rs.getInt(2);
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(3).toString();
                        String format = "%-20s %5d\n";
                        returnMessage += String.format("%-20s%10d%10s\n", name1, no, name2);
                        //returnMessage += " "+name1+" \t\t"+no+" \t\t"+name2 +" "+ System.lineSeparator();
                    }else if(queryNumber == 3){
                        if(once == false){
                            returnMessage += String.format("%-26s%s\n", "COMPANY_NAME", "TOTAL_COST");
                            once = true;}
                        String name = rs.getString(1).toString();
                        int no = rs.getInt(2);
                        returnMessage += String.format("%-26s%d\n", name, no);
                        //returnMessage += " "+name+" \t\t"+no+" "+ System.lineSeparator();
                    }else if(queryNumber == 4){
                        //String name = rs.getString(1).toString();
                        //returnMessage += ""+name+" "+ System.lineSeparator();  
                        if(once == false){
                            returnMessage += String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", "per_id", "p_name", "company_id", "title", "start_date", "end_date");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        String name3 = rs.getString(3).toString();
                        String name4 = rs.getString(4).toString();
                        String name5 = rs.getString(5).toString();
                        String name6 = rs.getString(6).toString();                        
                        returnMessage += String.format("%-26s %-15s %-15s %-15s %-15s %-15s\n", name1, name2, name3, name4, name5, name6);
                        
                    }else if(queryNumber == 5){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s\n", "title", "ks_code");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        returnMessage += String.format("%-26s %-15s\n", name1, name2);
                    }else if(queryNumber == 6){
                        if(once == false){
                            returnMessage += String.format("%-26s\n", "ks_code");
                            once = true;}
                        String name = rs.getString(1).toString();
                        returnMessage += String.format("%-26s \n", name);
                        returnMessage += ""+name+" "+ System.lineSeparator();
                    }else if(queryNumber == 7){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s\n", "title", "ks_code");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        returnMessage += String.format("%-26s %-15s\n", name1, name2);
                    }else if(queryNumber == 8){
                        if(once == false){                            
                            returnMessage += String.format("%-26s %-15s\n", "title", "ks_code");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        returnMessage += String.format("%-26s %-15s\n", name1, name2);
                    }else if(queryNumber == 9){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s\n", "c_code", "c_title");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        returnMessage += String.format("%-26s %-15s\n", name1, name2);
                    }else if(queryNumber == 10){
                        if(once == false){
                            returnMessage += String.format("%-20s %-20s %-20s %-20s\n", "c_code", "title", "month", "day");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        String name3 = rs.getString(3).toString();
                        String name4 = rs.getString(4).toString();
                        returnMessage += String.format("%-26s %-15s %-15s %-15s\n", name1, name2, name3, name4);
                    }else if(queryNumber == 11){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s\n", "c_code", "c_title");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        int no = rs.getInt(3);
                        returnMessage += String.format("%-26s %-15s %-15d", name1, name2, no);
                    }else if(queryNumber == 12){
                        if(once == false){
                            returnMessage += String.format("%-20s %-15s %-15s\n", "csetID", "set_size", "price");
                            once = true;}
                        String name = rs.getString(1).toString();
                        int no1 = rs.getInt(2);
                        int no2 = rs.getInt(3);
                        returnMessage += String.format("%-20s %-15d %-15d\n", name, no1, no2);
                    }else if(queryNumber == 13){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s\n", "jp_code", "title");
                            //returnMessage += String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", "per_id", "p_name", "company_id", "title", "start_date", "end_date");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        returnMessage += String.format("%-26s %-15s\n", name1, name2);
                    }else if(queryNumber == 14){
                        if(once == false){
                            returnMessage += String.format("%-20s %-15s %-15s %-15s\n", "job_code", "title", "pay_rate", "pay_type");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        String name3 = rs.getString(3).toString();
                        String name4 = rs.getString(4).toString();
                        returnMessage += String.format("%-20s %-15s %-15s %-15s\n", name1, name2, name3, name4);
                    }else if(queryNumber == 15){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s\n", "p_name", "email");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        returnMessage += String.format("%-26s %-15s\n", name1, name2);
                    }else if(queryNumber == 16){
                        if(once == false){
                            returnMessage += String.format("%-26s\n", "per_id");
                            once = true;}
                        String name = rs.getString(1).toString();
                        returnMessage += String.format("%-26s\n", name);
                    }else if(queryNumber == 17){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s\n", "KS_CODE", "PPL_MISSING_SKILL_FOR_JOB");
                            once = true;}
                        String name = rs.getString(1).toString();
                        int no = rs.getInt(2);
                        returnMessage += String.format("%-26s %-15d\n", name, no);
                    }
                    //skip for now.
                    else if(queryNumber == 18){
                        if(once == false){
                            returnMessage += String.format("%-20s %-20s\n", "per_id", "amnt_missing");
                            once = true;}
                        String name = rs.getString(1).toString();
                        int no = rs.getInt(2);
                        returnMessage += String.format("%-26s %-15d\n", name, no);
                    }else if(queryNumber == 19){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s\n", "per_id", "amnt_missing");
                            once = true;}
                        String name = rs.getString(1).toString();
                        int no = rs.getInt(2);
                        returnMessage += String.format("%-26s %-15d\n", name, no);
                    }else if(queryNumber == 20){
                        if(once == false){
                            returnMessage += String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", "per_id", "p_name", "company_id", "title", "start_date", "end_date");
                            once = true;}
                        String name = rs.getString(1).toString();
                        returnMessage += ""+name+" "+ System.lineSeparator();
                    }else if(queryNumber == 21){
                        if(once == false){
                            returnMessage += String.format("%-20s\n", "per_id");
                            once = true;}
                        String name = rs.getString(1).toString();
                        returnMessage += ""+name+" "+ System.lineSeparator();
                    }else if(queryNumber == 22){
                        if(once == false){
                            returnMessage += String.format("%-26s\n", "per_id");
                            once = true;}
                        String name = rs.getString(1).toString();
                        returnMessage += String.format("%-26s\n", name);
                    }else if(queryNumber == 23){
                        if(once == false){
                            returnMessage += String.format("%-20s %-20s %-20s\n", "company_name", "employee_num", "sum(total_salary)");
                            once = true;}
                        String name = rs.getString(1).toString();
                        int no1 = rs.getInt(2);
                        int no2 = rs.getInt(3);
                        returnMessage += String.format("%-30s%-25d%-20d\n", name, no1,no2);
                    }else if(queryNumber == 24){
                        if(once == false){
                            returnMessage += String.format("%-20s %-15s\n", "primary_sector", "all_employees");
                            once = true;}
                        String name = rs.getString(1).toString();
                        int no1 = rs.getInt(2);
                        returnMessage += String.format("%-20s %-15d\n", name, no1);
                    }else if(queryNumber == 242){
                        if(once == false){
                            returnMessage += String.format("%-20s %-15s\n", "primary_sector", "sector_total_cost");
                            once = true;}
                        String name = rs.getString(1).toString();
                        int no1 = rs.getInt(2);
                        returnMessage += String.format("%-20s %-15d\n", name, no1);
                    }else if(queryNumber == 25){
                        if(once == false){
                            returnMessage += String.format("%-20s\n", "pay_decreasing_percent");
                            once = true;}
                         float no = rs.getFloat(1);
                        returnMessage += String.format("%-20f\n",  no);
                    }else if(queryNumber == 252){
                        if(once == false){
                            returnMessage += String.format("%-20s %-20s %-20s\n", "company_name", "primary_sector", "sum(yearly_change/totalpeople)");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                         int no = rs.getInt(3);
                        returnMessage += String.format("%-20s %-20s %-20d\n", name1, name2, no);
                    }
                    else if(queryNumber == 26){
                        if(once == false){
                            returnMessage += String.format("%-26s %-15s %-15s %-15s\n", "TITLE", "JP_CODE", "NUM_QUALIFIED", "VACANCIES");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        String name2 = rs.getString(2).toString();
                        int no1 = rs.getInt(3);
                        int no2 = rs.getInt(4);
                        returnMessage += String.format("%0$-26s %0$-15s %0$-15d %0$-15d\n", name1, name2, no1, no2);
                    }else if(queryNumber == 27){
                        if(once == false){
                            returnMessage += String.format("%-20s %-15s %-15s %-15s %-15s\n", "jp_code"," num_unqualif"," c_for_job","c_title ","ks_code");
                            once = true;}
                        String name1 = rs.getString(1).toString();
                        int no = rs.getInt(2);
                        String name2 = rs.getString(3).toString();
                        String name3 = rs.getString(4).toString();
                        String name4 = rs.getString(5).toString();
                        returnMessage += String.format("%-20s %-15d %-15s %-15s %-15s\n", name1, no, name2, name3, name4);
                    }else if(queryNumber == 28){
                        if(once == false){
                            returnMessage += String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", "per_id", "p_name", "company_id", "title", "start_date", "end_date");
                            once = true;}
                        String name = rs.getString(1).toString();
                        returnMessage += ""+name+" "+ System.lineSeparator();
                    }else{
                        int no = rs.getInt(1);
                        String name = rs.getString(2).toString();
                        returnMessage += ""+ no +" "+name+""+ System.lineSeparator();
                        //System.out.println(""+ no +" "+name);
                    }                    
                }
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return returnMessage;
    }
}
