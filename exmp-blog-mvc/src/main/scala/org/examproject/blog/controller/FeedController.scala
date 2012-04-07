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

package org.examproject.blog.controller

import java.util.ArrayList
import java.util.Date
import java.util.List
import javax.inject.Inject
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

import org.examproject.blog.dto.EntryDto
import org.examproject.blog.model.FeedModel
import org.examproject.blog.service.FeedService

import scala.collection.JavaConversions._

/**
 * the feed controller class of the application.
 *
 * @author hiroxpepe
 */
@Controller
class FeedController {
 
    private val LOG: Logger = LoggerFactory.getLogger(
        classOf[FeedController]
    )
    
    @Inject
    private val context: ApplicationContext = null
    
    @Inject
    private val servletContext: ServletContext = null
    
    @Inject
    private val request: HttpServletRequest = null

    @Inject
    private val feedService: FeedService = null
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    ///////////////////////////////////////////////////////////////////////////
    /**
     * rss feed request.
     * expected HTTP request is '/entry/feed.rss'
     */
    @RequestMapping(
        value=Array("/entry/feed.rss"),
        method=Array(RequestMethod.GET)
    )
    def getFeedInRss()
    : ModelAndView = {
        LOG.info("called")
        
        // create the model-view object of spring mvc.
        val mv: ModelAndView = new ModelAndView()
        
        // set the view name for render.
        mv.setViewName(
            "rssFeedView"
        )
        
        // set the model-object to render.
        mv.addObject(
            "feedModel",
            // get the feed-model object list.
            getFeedModelList()
        )

        return mv
    }
    
    ///////////////////////////////////////////////////////////////////////////
    /**
     * atom feed request.
     * expected HTTP request is '/entry/feed.atom'
     */
    @RequestMapping(
        value=Array("/entry/feed.atom"),
        method=Array(RequestMethod.GET)
    )
    def getFeedInAtom()
    : ModelAndView = {
        LOG.info("called")
        
        // create the model-view object of spring mvc.
        val mv: ModelAndView = new ModelAndView()
        
        // set the view name for render.
        mv.setViewName(
            "atomFeedView"
        )
        
        // set the model-object to render.
        mv.addObject(
            "feedModel",
            // get the feed-model object list.
            getFeedModelList()
        )

        return mv
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // private methods
    
    private def getFeedModelList()
    : List[FeedModel] = {
        
        val feedModelList: List[FeedModel] = new ArrayList[FeedModel]()
        
        // get the dto-object list from service-object.
        val entryDtoList: List[EntryDto] = feedService.findAllEntry()
        
        // get the server URL of the request.
        var fullUrl: StringBuffer = request.getRequestURL()
        var serverUrlArray = fullUrl.toString().split("/entry")
    
        // process the entry object of all of the list.
        for (entryDto: EntryDto <- entryDtoList) {
            
            // create a object to build to the feed.
            val feedModel: FeedModel = context.getBean(
                classOf[FeedModel]
            )
            
            // set the value to the object.
            feedModel.setTitle(
                entryDto.getTitle()
            )
            feedModel.setSummary(
                entryDto.getContent()
            )
            feedModel.setCreatedDate(
                entryDto.getCreated()
            )
            
            // create the permalink url.
            feedModel.setUrl(
               serverUrlArray(0) + "/entry/" + entryDto.getCode + ".html"
            )
            
            // add the object to the object list.
            feedModelList.add(
                feedModel
            )
        }
        
        return feedModelList
    } 

}
