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

///////////////////////////////////////////////////////////////////////////////
/**
 * a controller class of the application.
 * 
 * @author h.adachi
 */
exmp.blog.core.Controller = window; {
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    /**
     * the initialization method of the Controller class.
     * this method should be called.
     */
    exmp.blog.core.Controller.init = function() {
        
        var controller = exmp.blog.core.Controller;
        
        controller._initializeComponent();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // event handler methods
    
    /**
     * an event handler that called when 
     * the button of post is clicked.
     */
    exmp.blog.core.Controller._postButtonOnClick = function() {
        
        var entryPostClosure = exmp.blog.functor.request.EntryPostClosure;
        var entryFactory = exmp.blog.functor.value.EntryFactory;
        
        entryPostClosure.execute(
            entryFactory.create()
        );
    }
    
    /**
     * an event handler that called when 
     * the button of setting is clicked.
     */
    exmp.blog.core.Controller._settingButtonOnClick = function() {
        
        var settingClosure = exmp.blog.functor.request.SettingClosure;
        var entryFactory = exmp.blog.functor.value.EntryFactory;
        
        settingClosure.execute(
            entryFactory.create()
        );
    }
    
    /**
     * an event handler that called when 
     * the div of message close is clicked.
     */
    exmp.blog.core.Controller._messageDivOnClick = function() {
        $("#message-block")
            .removeClass("show")
            .css({
                margin: "0",
                padding: "0",
                border: "none"
            })
            .html("");
    }
    
    /**
     * an event handler that called when
     * the div of title is clicked.
     */
    exmp.blog.core.Controller._headerTitleDivOnClick = function() {
        $("div.container")
            .toggleClass(
                "wide", 300
            );
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // private methods
    
    /**
     * initializes a div of the tabs area.
     */
    exmp.blog.core.Controller._initializeTabsDiv = function() {
        $("div.tab-content div.tab").hide();
        $("div.tab-content div.tab:first").show();
        $("div.tab-content ul li:first").addClass("active");
        $("div.tab-content ul li a").click(function(){
            $("div.tab-content ul li").removeClass("active");
            $(this).parent().addClass("active");
            var currentTab = $(this).attr("href");
            $("div.tab-content div.tab").hide();
            $(currentTab).show();
            return false;
        });
    }
    
    /**
     * initializes a div of entry list.
     * a HTTP request of Ajax for get the entry data.
     */
    exmp.blog.core.Controller._initializeEntryListDiv = function() {
        
        var profileUpdateClosure = exmp.blog.functor.dhtml.ProfileUpdateClosure;
        var entryListClosure = exmp.blog.functor.request.EntryListClosure;
        var entryFactory = exmp.blog.functor.value.EntryFactory;
        var pageUrl = location.href;
        
        profileUpdateClosure.execute({
            username: $("#entry_username").val(),
            email: $("#entry_email").val()
        });
        
        if (!(pageUrl.indexOf("entry/form.html") == -1)) {
            entryListClosure.execute(
                entryFactory.create()
            );
        }
    }
    
    /**
     * initializes a category select of form.
     * FIXME: a HTTP request of Ajax for get the data.. ? 
     */
    exmp.blog.core.Controller._initializeCategorySelect = function() {
        $("#entry-category").append($('<option value="General">General</option>'));
        $("#entry-category").append($('<option value="Technology">Technology</option>'));
        $("#entry-category").append($('<option value="Language">Language</option>'));
        $("#entry-category").append($('<option value="Music">Music</option>'));
        $("#entry-category").append($('<option value="Status">Status</option>'));
        $("#entry-category").val("General");
    }
    
    /**
     * initialize a component of the view class.
     */
    exmp.blog.core.Controller._initializeComponent = function() {
        
        var controller = exmp.blog.core.Controller;
        
        // calls for the initialization methods.
        
        controller._initializeEntryListDiv();
        
        controller._initializeTabsDiv();
        
        controller._initializeCategorySelect();
        
        // set the control's event handler.
        
        $("#post-button").click(function() {
            controller._postButtonOnClick();
        });
        
        $("#setting-button").click(function() {
            controller._settingButtonOnClick();
        });
        
        $("#message-block").click(function() {
            controller._messageDivOnClick();
        });
        
        $("span.header-title").click(function() {
            controller._headerTitleDivOnClick();
        });
    }
}
