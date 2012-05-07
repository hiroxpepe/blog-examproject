/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package org.examproject.blog.entity

import java.lang.Long
import java.io.Serializable
import java.util.Date
import java.util.HashSet
import java.util.Set
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

import org.springframework.stereotype.Component

import scala.collection.JavaConversions._
import scala.SerialVersionUID
import scala.reflect.BeanProperty

/**
 * @author hiroxpepe
 */
@Entity
@Table(name="entries")
@Component
@SerialVersionUID(-8712872385957386182L)
class Entry extends Serializable {
  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true)
    @BeanProperty
    var id: Long = _
    
    @Column(name="code", unique=true)
    @BeanProperty
    var code: String = _
    
    @Column(name="created")
    @Temporal(TemporalType.TIMESTAMP)
    @BeanProperty
    var created: Date = _
    
    @Column(name="updated")
    @Temporal(TemporalType.TIMESTAMP)
    @BeanProperty
    var updated: Date = _
    
    @Column(name="author")
    @BeanProperty
    var author: String = _
    
//    @Column(name="title")
//    @BeanProperty
//    var title: String = _
//    
//    @Column(name="content", length=2048)
//    @BeanProperty
//    var content: String = _
    
    @OneToMany(mappedBy="entry")
    @BeanProperty
    var paragraphSet: Set[Paragraph] = new HashSet[Paragraph]()
    
    @OneToMany(mappedBy="entry")
    @BeanProperty
    var tagItemSet: Set[TagItem] = new HashSet[TagItem]()
    
    @ManyToOne
    @BeanProperty
    var user: User = _
    
    @ManyToOne
    @BeanProperty
    var subject: Subject = _
    
}