package com.softwaremill.codebrag.service.config

import com.typesafe.config.Config

trait CodebragConfig {
  def rootConfig: Config

  lazy val localGitStoragePath: String = rootConfig.getString("codebrag.local-git-storage-path")
  lazy val codebragSyncUserLogin: String = rootConfig.getString("codebrag.sync-user-login")
  lazy val debugServicesLogin: String = rootConfig.getString("codebrag.debug-services-login")
  lazy val debugServicesPassword: String = rootConfig.getString("codebrag.debug-services-password")
}
