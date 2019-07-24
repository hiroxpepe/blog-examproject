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

package org.examproject.blog.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.examproject.blog.dto.EntryDto;
import org.examproject.blog.form.EntryForm;
import org.examproject.blog.service.EntryService;
import org.examproject.blog.response.EntryResponse;

/**
 * the entry controller class of the application.
 *
 * @author hiroxpepe
 */
@Controller
public class SettingController {

    private Logger LOG = LoggerFactory.getLogger(SettingController.class);

    @Inject
    private ApplicationContext context = null;

    @Inject
    private HttpServletRequest request = null;

    @Inject
    private Mapper mapper = null;

    @Inject
    private EntryService entryService = null;

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    ///////////////////////////////////////////////////////////////////////////
    /**
     * store the configuration data to the cookie.
     * expected Ajax HTTP request is '/entry/setting.html'
     */
    @RequestMapping(
        value="/entry/setting.html",
        method=RequestMethod.POST,
        headers="Accept=application/json"
    )
    public String doSetting(
        @RequestBody
        EntryForm entryForm,
        HttpServletResponse response
    ) {
        LOG.info("called");

        // store the setting param to the cookie.
        storeToCookie(
            entryForm,
            response,
            604800
        );

        // redirect the request to the 'entry/form' page.
        return "redirect:/entry/form.html";
    }

    ///////////////////////////////////////////////////////////////////////////
    /**
     * if an error is occured, this method will be called.
     */
    @ExceptionHandler
    @ResponseBody
    public EntryResponse handleException(
        Exception e
    ) {
        LOG.info("called");
        LOG.error(e.getMessage());

        // create a response-object.
        EntryResponse response = context.getBean(EntryResponse.class);

        // notify the occurrence of errors to the html page.
        response.setIsError(true);
        return response;
    }

    ///////////////////////////////////////////////////////////////////////////
    // private methods

    ///////////////////////////////////////////////////////////////////////////
    /**
     * store the configuration data to the cookie.
     */
    private void storeToCookie(
        EntryForm entryForm,
        HttpServletResponse response,
        int maxAge
    ) {
        LOG.debug("called");

        val username = new Cookie(
            "__exmp_blog_username", entryForm.getUsername()
        );
        username.setMaxAge(maxAge);
        response.addCookie(username);

        val password = new Cookie(
            "__exmp_blog_password", entryForm.getPassword()
        );
        password.setMaxAge(maxAge);
        response.addCookie(password);

        val author = new Cookie(
            "__exmp_blog_author", entryForm.getAuthor()
        );
        author.setMaxAge(maxAge);
        response.addCookie(author);

        val email = new Cookie(
            "__exmp_blog_email", entryForm.getEmail()
        );
        email.setMaxAge(maxAge);
        response.addCookie(email);
    }

}
