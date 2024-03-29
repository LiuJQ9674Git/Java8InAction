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
package org.mybatis.scala

/** Provides main configuration classes.
  * == Basic usage ==
  * Usual steps are:
  *  - Load the configuration from an external XML file
  *  - Add one or more configuration spaces with mapped statements
  *  - Create the persistenceContext
  *
  * == Code sample ==
  * {{{
  * val config = Configuration("META-INF/mybatis.xml")
  * config.addSpace("ns") { space =>
  *   space ++= MyDAO
  * }
  * val persistenceContext = config.createPersistenceContext
  * }}}
  * 
  * == Another example without external XML file and with default namespace ==
  * {{{
  * val config = Configuration(
  *  Environment(
  *    "default", 
  *    new JdbcTransactionFactory(), 
  *    new PooledDataSource(
  *      "org.hsqldb.jdbcDriver",
  *      "jdbc:hsqldb:mem:scala",
  *      "sa",
  *      ""
  *    )
  *  )
  * )
  * config ++= MyDAO
  * 
  * val persistenceContext = config.createPersistenceContext
  * }}}
  */
package object config {

  type TransactionFactory = org.apache.ibatis.transaction.TransactionFactory
  type JdbcTransactionFactory = org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
  type ManagedTransactionFactory = org.apache.ibatis.transaction.managed.ManagedTransactionFactory
  
  type PooledDataSource = org.apache.ibatis.datasource.pooled.PooledDataSource
  type UnpooledDataSource = org.apache.ibatis.datasource.unpooled.UnpooledDataSource
  type JndiDataSourceFactory = org.apache.ibatis.datasource.jndi.JndiDataSourceFactory
  
  type ObjectFactory = org.apache.ibatis.reflection.factory.ObjectFactory
  type ObjectWrapperFactory = org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory
  type DatabaseIdProvider = org.apache.ibatis.mapping.DatabaseIdProvider
  type LanguageDriver = org.apache.ibatis.scripting.LanguageDriver
  
  sealed abstract class LocalCacheScope {
    val unwrap : org.apache.ibatis.session.LocalCacheScope
    case object SESSION extends LocalCacheScope { val unwrap = org.apache.ibatis.session.LocalCacheScope.SESSION }
    case object STATEMENT extends LocalCacheScope { val unwrap = org.apache.ibatis.session.LocalCacheScope.STATEMENT }
  }
  
  sealed abstract class AutoMappingBehavior {
    val unwrap : org.apache.ibatis.session.AutoMappingBehavior
    case object FULL extends AutoMappingBehavior { val unwrap = org.apache.ibatis.session.AutoMappingBehavior.FULL }
    case object NONE extends AutoMappingBehavior { val unwrap = org.apache.ibatis.session.AutoMappingBehavior.NONE }
    case object PARTIAL extends AutoMappingBehavior { val unwrap = org.apache.ibatis.session.AutoMappingBehavior.PARTIAL }
  }
  
}