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
 * a functor class of the application.
 * send HTTP request for the entry delete.
 * 
 * @author hiroxpepe
 */
exmp.blog.functor.request.EntryDeleteClosure = {
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    execute: function(obj) {
        console.log("exmp.blog.functor.request.EntryDeleteClosure#execute");
        
        var waitingMessageClosure = exmp.blog.functor.dhtml.WaitingMessageClosure;
        
        var successMessageClosure = exmp.blog.functor.dhtml.SuccessMessageClosure;
        
        var errorMessageClosure = exmp.blog.functor.dhtml.ErrorMessageClosure;
        
        var entryListUpdateClosure = exmp.blog.functor.dhtml.EntryListUpdateClosure;
        
        var eventBuildClosure = exmp.blog.functor.event.EventBuildClosure;
        
        // show the waiting message.
        waitingMessageClosure.execute({
            message: "please wait..."
        });
        
        // create an ajax object.
        new $.ajax({
            url: "delete.html",
            type: "POST",
            data: {
                code: obj.code
            },
            dataType: "json",
            
            // callback function of the success.
            success: function(data, dataType) {
                
                // if get a error from the response.
                if (data.isError) {
                    // show the error message.
                    errorMessageClosure.execute({
                        message: "application error occurred.."
                    });
                    return;
                }
                
                // update the HTML table of the entry list.
                entryListUpdateClosure.execute(
                    data
                );
                    
                // build the event of the entry list.
                eventBuildClosure.execute(
                    data
                );
                
                $("#entry_title").val("");
                $("#entry_content").val("");
                
                // show the success message.
                successMessageClosure.execute({
                    message: "complete."
                });
            },
            
            // callback function of the error.
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                
                // show the error message.
                errorMessageClosure.execute({
                    message: "httprequest error occurred.."
                });
            }
        });
    }
}