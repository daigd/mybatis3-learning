package demo

import java.io.File
import java.sql.{Connection, DriverManager, Statement}

import common.{DBConfig, UnitSpec}
import org.apache.ibatis.io.Resources
import org.apache.ibatis.jdbc.{ScriptRunner, SqlRunner}

import scala.reflect.io.Directory

/**
 * @author : DaiGD
 * @createtime :  2020年12月03日 16:01
 * @description : H2DB演示
 * 参考 https://medium.com/@sukumaarneo/testing-embedded-h2-db-with-scala-and-scalatest-3a863aafc9ab
 */
class H2DBDemoTest extends UnitSpec with DBConfig{
  
  "test H2 embedded database" should "Success" in{
    var row1InsertionCheck = false
    val con: Connection = DriverManager.getConnection(DATABASE_URL)
    val stm: Statement = con.createStatement
    val sql: String =
      """
        |drop table test_table1 if exists;
        |create table test_table1(ID INT PRIMARY KEY,NAME VARCHAR(500));
        |insert into test_table1 values (1,'A');""".stripMargin

    stm.execute(sql)
    val rs = stm.executeQuery("select * from test_table1")

    rs.next
    row1InsertionCheck = (1 == rs.getInt("ID")) && ("A" == rs.getString("NAME"))

    assert(row1InsertionCheck, "Data not inserted")
  }
  
  "使用SQL脚本文件初始化数据库" should "Success" in {

    Given("创建数据库连接")
    val con: Connection = DriverManager.getConnection(DATABASE_URL)
    And("执行脚本")
    val scriptRunner: ScriptRunner = new ScriptRunner(con)
    scriptRunner.setLogWriter(null)
    scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"))
    scriptRunner.runScript(Resources.getResourceAsReader("init-data.sql"))
    
    When("执行SQL")
    // 使用 Mybatis 的 ScriptRunner 工具执行脚本
    val sqlRunner: SqlRunner = new SqlRunner(con)
    val results = sqlRunner.selectAll("select * from user")
    
    Then("结果断言")
    assert(results.size() == 2)
    And("结果输出")
    results.forEach(r=> println(r))
    con.close()
  }
  

  override def afterAll() {
    new Directory(new File(PARENT_DIR)).deleteRecursively()
  }
}
