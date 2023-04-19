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
package org.mybatis.scala.mapping

/** Abstract SQL statement mapping.
  * @version \$Revision$
  */
trait Statement {

  /** Fully qualified identifier of the statement.
    * Autogenerated by the configuration space.
    */
  var fqi : FQI = null

  /** Any one of STATEMENT, PREPARED or CALLABLE.
    * This causes MyBatis to use Statement, PreparedStatement or CallableStatement respectively.
    * Default: PREPARED.
    */
  var statementType : StatementType = StatementType.PREPARED

  /** This sets the maximum time the driver will wait for the database to return from a request, before throwing an exception.
    * Default is unset (driver dependent).
    */
  var timeout : Int = -1

  /** Setting this to true will cause the cache to be flushed whenever this statement is called.
    * Default: false for select statements.
    */
  var flushCache : Boolean = true

  /** Vendor ID */
  var databaseId : String = null

  /** Scripting driver */
  var languageDriver = org.mybatis.scala.config.DefaultScriptingDriver 
  
  /** Dynamic SQL definition, an xml node with root &lt;xsql&gt;
    * == Code sample ==
    * As simple as an static SQL:
    * {{{
    *   def xsql = "SELECT * FROM mytable WHERE id = #{{id}}"
    * }}}
    *
    * Or using dynamic tags:
    * {{{
    *   def xsql =
    *     <xsql>
    *       SELECT *
    *       FROM mytable
    *       <where>
    *         <if test="name != null">
    *           name like #{{name}}
    *         </if>
    *       </where>
    *     <xsql>
    * }}}
    */
  def xsql : XSQL

  /** Returns the Class of the input parameter. */
  def parameterTypeClass : Class[_]

  def execute[A](command : => A) : A = fqi match {
    case null =>
      throw new org.mybatis.scala.config.ConfigurationException("Unknown Statement " + this.getClass.getName)
    case _ =>
      command
  }

}