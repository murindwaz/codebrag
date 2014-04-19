package com.softwaremill.codebrag.rest

import org.scalatra._
import com.softwaremill.codebrag.service.user.Authenticator
import org.bson.types.ObjectId
import com.typesafe.scalalogging.slf4j.Logging
import com.softwaremill.codebrag.dao.user.UserDAO
import com.softwaremill.codebrag.activities.{IncomingSettings, ChangeUserSettingsUseCase}

class UsersSettingsServlet(val authenticator: Authenticator, userDao: UserDAO, changeUserSettings: ChangeUserSettingsUseCase) extends JsonServletWithAuthentication with Logging {

  get("/") {
    haltIfNotAuthenticated()
    userDao.findById(new ObjectId(user.id)) match {
      case Some(user) => Map("userSettings" -> user.settings)
      case None => NotFound
    }
  }

  put("/") {
    val newSettings = IncomingSettings(extractOptBoolean("emailNotificationsEnabled"), extractOptBoolean("dailyUpdatesEmailEnabled"), extractOptBoolean("appTourDone"))
    changeUserSettings.execute(user, newSettings).left.map { err =>
      halt(400, Map("error" -> err))
    }
  }

}
