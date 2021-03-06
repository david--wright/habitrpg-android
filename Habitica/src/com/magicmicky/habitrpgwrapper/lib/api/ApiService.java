package com.magicmicky.habitrpgwrapper.lib.api;

import com.habitrpg.android.habitica.ui.activities.SetupActivity;
import com.magicmicky.habitrpgwrapper.lib.models.ChatMessage;
import com.magicmicky.habitrpgwrapper.lib.models.ContentResult;
import com.magicmicky.habitrpgwrapper.lib.models.Group;
import com.magicmicky.habitrpgwrapper.lib.models.HabitRPGUser;
import com.magicmicky.habitrpgwrapper.lib.models.PostChatMessageResult;
import com.magicmicky.habitrpgwrapper.lib.models.Status;
import com.magicmicky.habitrpgwrapper.lib.models.Tag;
import com.magicmicky.habitrpgwrapper.lib.models.TaskDirectionData;
import com.magicmicky.habitrpgwrapper.lib.models.UserAuth;
import com.magicmicky.habitrpgwrapper.lib.models.UserAuthResponse;
import com.magicmicky.habitrpgwrapper.lib.models.UserAuthSocial;
import com.magicmicky.habitrpgwrapper.lib.models.responses.UnlockResponse;
import com.magicmicky.habitrpgwrapper.lib.models.tasks.ItemData;
import com.magicmicky.habitrpgwrapper.lib.models.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by MagicMicky on 10/06/2014.
 */
public interface ApiService {
    @GET("/status")
    void getStatus(Callback<Status> statusCallback);

    @GET("/content")
    void getContent(Callback<ContentResult> contentResultCallback);

    /* user API */

    @GET("/user/")
    void getUser(Callback<HabitRPGUser> habitRPGUserCallback);

    @PUT("/user/")
    void updateUser(@Body Map<String, Object> updateDictionary, Callback<HabitRPGUser> habitRPGUserCallback);

    @GET("/user/inventory/buy")
    void getInventoryBuyableGear(Callback<List<ItemData>> buyableGearCallback);

    @POST("/user/inventory/buy/{key}")
    void buyItem(@Path("key") String itemKey, Callback<Void> voidCallback);

    @POST("/user/unlock")
    void unlockPath(@Query("path") String path, Callback<UnlockResponse> unlockResponseCallback);

    @GET("/user/tasks/{id}")
    void getTask(@Path("id") String id, Callback<Task> habitItemCallback);

    @POST("/user/tasks/{id}/{direction}")
    void postTaskDirection(@Path("id") String id, @Path("direction") String direction, Callback<TaskDirectionData> taskDirectionCallback);

    @POST("/user/tasks")
    void createItem(@Body Task item, Callback<Task> habitItemCallback);


    @PUT("/user/tasks/{id}")
    void updateTask(@Path("id") String id, @Body Task item, Callback<Task> habitItemCallback);


    @DELETE("/user/tasks/{id}")
    void deleteTask(@Path("id") String id, Callback<Void> voidCallback);


    @POST("/user/tags")
    void createTag(@Body Tag tag, Callback<List<Tag>> multiTagCallback);


    @PUT("/user/tags/{id}")
    void updateTag(@Path("id") String id, @Body Tag tag, Callback<Tag> multiTagCallback);


    @DELETE("/user/tags/{id}")
    void deleteTag(@Path("id") String id, Callback<Void> voidCallback);

    @POST("/register")
    void registerUser(@Body UserAuth auth, Callback<UserAuthResponse> callback);

    @POST("/user/auth/local")
    void connectLocal(@Body UserAuth auth, Callback<UserAuthResponse> callback);

    @POST("/user/auth/social")
    void connectSocial(@Body UserAuthSocial auth, Callback<UserAuthResponse> callback);

    @POST("/user/sleep")
    void sleep(Callback<Void> voidCallback);

    @POST("/user/revive")
    void revive(Callback<HabitRPGUser> habitRPGUserCallback);

    @POST("/user/class/cast/{skill}")
    void useSkill(@Path("skill") String skillName, @Query("targetType") String targetType, @Query("targetId") String targetId, Callback<HabitRPGUser> habitRPGUserCallback);

    @POST("/user/class/cast/{skill}")
    void useSkill(@Path("skill") String skillName, @Query("targetType") String targetType, Callback<HabitRPGUser> habitRPGUserCallback);

    /* Group API */

    @GET("/groups")
    void listGroups(@Query("type") String type, Callback<ArrayList<Group>> cb);

    @GET("/groups/{gid}")
    void getGroup(@Path("gid") String groupId, Callback<Group> cb);

    @GET("/groups/{gid}/chat")
    void listGroupChat(@Path("gid") String groupId, Callback<List<ChatMessage>> cb);

    @POST("/groups/{gid}/chat")
    void postGroupChat(@Path("gid") String groupId, @Query("message") String message, Callback<PostChatMessageResult> cb);

    @DELETE("/groups/{gid}/chat/{messageId}")
    void deleteMessage(@Path("gid") String groupId, @Path("messageId") String messageId, Callback<Void> cb);

    // Like returns the full chat list
    @POST("/groups/{gid}/chat/{mid}/like")
    void likeMessage(@Path("gid") String groupId, @Path("mid") String mid, Callback<List<Void>> cb);

    @POST("/groups/{gid}/chat/{mid}/flag")
    void flagMessage(@Path("gid") String groupId, @Path("mid") String mid, Callback<Void> cb);

    @POST("/groups/{gid}/chat/seen")
    void seenMessage(@Path("gid") String groupId, Callback<Void> cb);

    @POST("/user/batch-update")
    void batchOperation(@Body List<Map<String,Object>> operations, Callback<HabitRPGUser> cb);
}
