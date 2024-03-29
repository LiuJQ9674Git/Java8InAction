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
package org.mybatis.scala.session

import org.apache.ibatis.session.{ExecutorType => ET}

sealed trait ExecutorType {
  val unwrap : ET
}

object ExecutorType {
  val SIMPLE  = new ExecutorType { val unwrap = ET.SIMPLE }
  val REUSE   = new ExecutorType { val unwrap = ET.REUSE }
  val BATCH   = new ExecutorType { val unwrap = ET.BATCH }
}
