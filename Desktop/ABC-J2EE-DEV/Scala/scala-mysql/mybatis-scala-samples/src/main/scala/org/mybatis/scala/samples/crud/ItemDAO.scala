/*
 *    Copyright 2011-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.scala.samples.crud

import org.mybatis.scala.mapping._
import org.mybatis.scala.mapping.Binding._
import org.mybatis.scala.mapping.TypeHandlers._
import org.mybatis.scala.session.Session
import scala.language.postfixOps

object ItemDAO {

  val ItemResultMap = new ResultMap[Item] {
    id ( column = "id_", property = "id" )
    result ( column = "description_", property = "description" )
    result ( column = "info_", property = "info", jdbcType=JdbcType.VARCHAR )
    result ( column = "year_", property = "year", typeHandler=T[OptIntegerTypeHandler] )
  }

  val SELECT_SQL =
    <xsql>
      SELECT *
      FROM item
    </xsql>

  val findAll = new SelectList[Item] {
    resultMap = ItemResultMap
    def xsql = SELECT_SQL
  }

  val findByDescription = new SelectListBy[String,Item] {
    resultMap = ItemResultMap
    def xsql =
      <xsql>
        {SELECT_SQL}
        WHERE description_ LIKE {"description"?}
        ORDER BY description_
      </xsql>
  }

  val findById = new SelectOneBy[Int,Item] {
    resultMap = ItemResultMap
    def xsql =
      <xsql>
        {SELECT_SQL}
        WHERE id_ = {"id"?}
      </xsql>
  }

  val insert = new Insert[Item] {
    def xsql =
      <xsql>
        INSERT INTO item(description_, info_, year_)
        VALUES ( 
          { "description"? }, 
          {? ("info", jdbcType=JdbcType.VARCHAR)},
          {? ("year", jdbcType=JdbcType.INTEGER)}
        )
      </xsql>
  }

  val update = new Update[Item] {
    def xsql =
      <xsql>
        UPDATE item
        SET 
          description_ = {"description"?},
          info_ = {? ("info", jdbcType=JdbcType.VARCHAR)},
          year_ = {? ("year", jdbcType=JdbcType.INTEGER)}
        WHERE id_ = {"id"?}
      </xsql>
  }

  val DELETE_SQL =
    <xsql>
      DELETE FROM item
      WHERE id_ = {"id"?}
    </xsql>

  val delete = new Delete[Item] {
    def xsql = DELETE_SQL
  }

  val deleteById = new Delete[Int] {
    def xsql = DELETE_SQL
  }

  val dropTable = new Perform {
    def xsql =
      <xsql>
        DROP TABLE IF EXISTS item
      </xsql>
  }

  val createTable = new Perform {
    def xsql =
      <xsql>
        CREATE TABLE item (
          id_ INTEGER GENERATED BY DEFAULT AS IDENTITY,
          description_ varchar(255) not null,
          info_ varchar(255),
          year_ integer,
          primary key (id_)
        )
      </xsql>
  }

  def initdb(implicit s : Session) = {
    dropTable()
    createTable()
  }

  def bind = Seq(delete,update,insert,findById,findByDescription,deleteById,findAll,createTable,dropTable)

}
