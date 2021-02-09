package com.github.Ox0017.vrc.model.dto.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteConfigDto {

	@JsonProperty("address")
	private String address;

	@JsonProperty("apiKey")
	private String apiKey;

	@JsonProperty("appName")
	private String appName;

	@JsonProperty("buildVersionTag")
	private String buildVersionTag;

	@JsonProperty("clientApiKey")
	private String clientApiKey;

	@JsonProperty("clientBPSCeiling")
	private Long clientBPSCeiling;

	@JsonProperty("clientReservedPlayerBPS")
	private Long clientReservedPlayerBPS;

	@JsonProperty("clientSentCountAllowance")
	private Long clientSentCountAllowance;

	@JsonProperty("contactEmail")
	private String contactEmail;

	@JsonProperty("copyrightEmail")
	private String copyrightEmail;

	@JsonProperty("currentTOSVersion")
	private Long currentTOSVersion;

	@JsonProperty("defaultAvatar")
	private String defaultAvatar;

	@JsonProperty("deploymentGroup")
	private String deploymentGroup;

	@JsonProperty("devAppVersionStandalone")
	private String devAppVersionStandalone;

	@JsonProperty("devDownloadLinkWindows")
	private String devDownloadLinkWindows;

	@JsonProperty("devSdkUrl")
	private String devSdkUrl;

	@JsonProperty("devSdkVersion")
	private String devSdkVersion;

	@JsonProperty("devServerVersionStandalone")
	private String devServerVersionStandalone;

	@JsonProperty("disableAvatarGating")
	private Boolean disableAvatarGating;

	@JsonProperty("disableCommunityLabs")
	private Boolean disableCommunityLabs;

	@JsonProperty("disableCommunityLabsPromotion")
	private Boolean disableCommunityLabsPromotion;

	@JsonProperty("disableEmail")
	private Boolean disableEmail;

	@JsonProperty("disableEventStream")
	private Boolean disableEventStream;

	@JsonProperty("disableFeedbackGating")
	private Boolean disableFeedbackGating;

	@JsonProperty("disableHello")
	private Boolean disableHello;

	@JsonProperty("disableRegistration")
	private Boolean disableRegistration;

	@JsonProperty("disableSteamNetworking")
	private Boolean disableSteamNetworking;

	@JsonProperty("disableTwoFactorAuth")
	private Boolean disableTwoFactorAuth;

	@JsonProperty("disableUdon")
	private Boolean disableUdon;

	@JsonProperty("disableUpgradeAccount")
	private Boolean disableUpgradeAccount;

	@JsonProperty("downloadLinkWindows")
	private String downloadLinkWindows;

	@JsonProperty("downloadUrls")
	private Map<String, String> downloadUrls;

	@JsonProperty("dynamicWorldRows")
	private List<DynamicWorldRowDto> dynamicWorldRows;

	@JsonProperty("events")
	private Map<String, Long> events;

	@JsonProperty("gearDemoRoomId")
	private String gearDemoRoomId;

	@JsonProperty("homeWorldId")
	private String homeWorldId;

	@JsonProperty("hubWorldId")
	private String hubWorldId;

	@JsonProperty("jobsEmail")
	private String jobsEmail;

	@JsonProperty("messageOfTheDay")
	private String messageOfTheDay;

	@JsonProperty("moderationEmail")
	private String moderationEmail;

	@JsonProperty("moderationQueryPeriod")
	private Long moderationQueryPeriod;

	@JsonProperty("notAllowedToSelectAvatarInPrivateWorldMessage")
	private String notAllowedToSelectAvatarInPrivateWorldMessage;

	@JsonProperty("plugin")
	private String plugin;

	@JsonProperty("releaseAppVersionStandalone")
	private String releaseAppVersionStandalone;

	@JsonProperty("releaseSdkUrl")
	private String releaseSdkUrl;

	@JsonProperty("releaseSdkVersion")
	private String releaseSdkVersion;

	@JsonProperty("releaseServerVersionStandalone")
	private String releaseServerVersionStandalone;

	@JsonProperty("sdkDeveloperFaqUrl")
	private String sdkDeveloperFaqUrl;

	@JsonProperty("sdkDiscordUrl")
	private String sdkDiscordUrl;

	@JsonProperty("sdkNotAllowedToPublishMessage")
	private String sdkNotAllowedToPublishMessage;

	@JsonProperty("sdkUnityVersion")
	private String sdkUnityVersion;

	@JsonProperty("serverName")
	private String serverName;

	@JsonProperty("supportEmail")
	private String supportEmail;

	@JsonProperty("timeOutWorldId")
	private String timeOutWorldId;

	@JsonProperty("tutorialWorldId")
	private String tutorialWorldId;

	@JsonProperty("useReliableUdpForVoice")
	private Boolean useReliableUdpForVoice;

	@JsonProperty("userUpdatePeriod")
	private Long userUpdatePeriod;

	@JsonProperty("updateRateMsMaximum")
	private Long updateRateMsMaximum;

	@JsonProperty("updateRateMsMinimum")
	private Long updateRateMsMinimum;

	@JsonProperty("updateRateMsNormal")
	private Long updateRateMsNormal;

	@JsonProperty("uploadAnalysisPercent")
	private String uploadAnalysisPercent;

	@JsonProperty("userVerificationDelay")
	private Long userVerificationDelay;

	@JsonProperty("userVerificationRetry")
	private Long userVerificationRetry;

	@JsonProperty("userVerificationTimeout")
	private Long userVerificationTimeout;

	@JsonProperty("viveWindowsUrl")
	private String viveWindowsUrl;

	@JsonProperty("whiteListedAssetUrls")
	private List<String> whiteListedAssetUrls;

	@JsonProperty("worldUpdatePeriod")
	private Long worldUpdatePeriod;

	@JsonProperty("youtubedl-hash")
	private String youtubeDlHash;

	@JsonProperty("youtubedl-version")
	private String youtubeDlVersion;

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(final String apiKey) {
		this.apiKey = apiKey;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(final String appName) {
		this.appName = appName;
	}

	public String getBuildVersionTag() {
		return this.buildVersionTag;
	}

	public void setBuildVersionTag(final String buildVersionTag) {
		this.buildVersionTag = buildVersionTag;
	}

	public String getClientApiKey() {
		return this.clientApiKey;
	}

	public void setClientApiKey(final String clientApiKey) {
		this.clientApiKey = clientApiKey;
	}

	public Long getClientBPSCeiling() {
		return this.clientBPSCeiling;
	}

	public void setClientBPSCeiling(final Long clientBPSCeiling) {
		this.clientBPSCeiling = clientBPSCeiling;
	}

	public Long getClientReservedPlayerBPS() {
		return this.clientReservedPlayerBPS;
	}

	public void setClientReservedPlayerBPS(final Long clientReservedPlayerBPS) {
		this.clientReservedPlayerBPS = clientReservedPlayerBPS;
	}

	public Long getClientSentCountAllowance() {
		return this.clientSentCountAllowance;
	}

	public void setClientSentCountAllowance(final Long clientSentCountAllowance) {
		this.clientSentCountAllowance = clientSentCountAllowance;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(final String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getCopyrightEmail() {
		return this.copyrightEmail;
	}

	public void setCopyrightEmail(final String copyrightEmail) {
		this.copyrightEmail = copyrightEmail;
	}

	public Long getCurrentTOSVersion() {
		return this.currentTOSVersion;
	}

	public void setCurrentTOSVersion(final Long currentTOSVersion) {
		this.currentTOSVersion = currentTOSVersion;
	}

	public String getDefaultAvatar() {
		return this.defaultAvatar;
	}

	public void setDefaultAvatar(final String defaultAvatar) {
		this.defaultAvatar = defaultAvatar;
	}

	public String getDeploymentGroup() {
		return this.deploymentGroup;
	}

	public void setDeploymentGroup(final String deploymentGroup) {
		this.deploymentGroup = deploymentGroup;
	}

	public String getDevAppVersionStandalone() {
		return this.devAppVersionStandalone;
	}

	public void setDevAppVersionStandalone(final String devAppVersionStandalone) {
		this.devAppVersionStandalone = devAppVersionStandalone;
	}

	public String getDevDownloadLinkWindows() {
		return this.devDownloadLinkWindows;
	}

	public void setDevDownloadLinkWindows(final String devDownloadLinkWindows) {
		this.devDownloadLinkWindows = devDownloadLinkWindows;
	}

	public String getDevSdkUrl() {
		return this.devSdkUrl;
	}

	public void setDevSdkUrl(final String devSdkUrl) {
		this.devSdkUrl = devSdkUrl;
	}

	public String getDevSdkVersion() {
		return this.devSdkVersion;
	}

	public void setDevSdkVersion(final String devSdkVersion) {
		this.devSdkVersion = devSdkVersion;
	}

	public String getDevServerVersionStandalone() {
		return this.devServerVersionStandalone;
	}

	public void setDevServerVersionStandalone(final String devServerVersionStandalone) {
		this.devServerVersionStandalone = devServerVersionStandalone;
	}

	public Boolean getDisableAvatarGating() {
		return this.disableAvatarGating;
	}

	public void setDisableAvatarGating(final Boolean disableAvatarGating) {
		this.disableAvatarGating = disableAvatarGating;
	}

	public Boolean getDisableCommunityLabs() {
		return this.disableCommunityLabs;
	}

	public void setDisableCommunityLabs(final Boolean disableCommunityLabs) {
		this.disableCommunityLabs = disableCommunityLabs;
	}

	public Boolean getDisableCommunityLabsPromotion() {
		return this.disableCommunityLabsPromotion;
	}

	public void setDisableCommunityLabsPromotion(final Boolean disableCommunityLabsPromotion) {
		this.disableCommunityLabsPromotion = disableCommunityLabsPromotion;
	}

	public Boolean getDisableEmail() {
		return this.disableEmail;
	}

	public void setDisableEmail(final Boolean disableEmail) {
		this.disableEmail = disableEmail;
	}

	public Boolean getDisableEventStream() {
		return this.disableEventStream;
	}

	public void setDisableEventStream(final Boolean disableEventStream) {
		this.disableEventStream = disableEventStream;
	}

	public Boolean getDisableFeedbackGating() {
		return this.disableFeedbackGating;
	}

	public void setDisableFeedbackGating(final Boolean disableFeedbackGating) {
		this.disableFeedbackGating = disableFeedbackGating;
	}

	public Boolean getDisableHello() {
		return this.disableHello;
	}

	public void setDisableHello(final Boolean disableHello) {
		this.disableHello = disableHello;
	}

	public Boolean getDisableRegistration() {
		return this.disableRegistration;
	}

	public void setDisableRegistration(final Boolean disableRegistration) {
		this.disableRegistration = disableRegistration;
	}

	public Boolean getDisableSteamNetworking() {
		return this.disableSteamNetworking;
	}

	public void setDisableSteamNetworking(final Boolean disableSteamNetworking) {
		this.disableSteamNetworking = disableSteamNetworking;
	}

	public Boolean getDisableTwoFactorAuth() {
		return this.disableTwoFactorAuth;
	}

	public void setDisableTwoFactorAuth(final Boolean disableTwoFactorAuth) {
		this.disableTwoFactorAuth = disableTwoFactorAuth;
	}

	public Boolean getDisableUdon() {
		return this.disableUdon;
	}

	public void setDisableUdon(final Boolean disableUdon) {
		this.disableUdon = disableUdon;
	}

	public Boolean getDisableUpgradeAccount() {
		return this.disableUpgradeAccount;
	}

	public void setDisableUpgradeAccount(final Boolean disableUpgradeAccount) {
		this.disableUpgradeAccount = disableUpgradeAccount;
	}

	public String getDownloadLinkWindows() {
		return this.downloadLinkWindows;
	}

	public void setDownloadLinkWindows(final String downloadLinkWindows) {
		this.downloadLinkWindows = downloadLinkWindows;
	}

	public Map<String, String> getDownloadUrls() {
		return this.downloadUrls;
	}

	public void setDownloadUrls(final Map<String, String> downloadUrls) {
		this.downloadUrls = downloadUrls;
	}

	public List<DynamicWorldRowDto> getDynamicWorldRows() {
		return this.dynamicWorldRows;
	}

	public void setDynamicWorldRows(final List<DynamicWorldRowDto> dynamicWorldRows) {
		this.dynamicWorldRows = dynamicWorldRows;
	}

	public Map<String, Long> getEvents() {
		return this.events;
	}

	public void setEvents(final Map<String, Long> events) {
		this.events = events;
	}

	public String getGearDemoRoomId() {
		return this.gearDemoRoomId;
	}

	public void setGearDemoRoomId(final String gearDemoRoomId) {
		this.gearDemoRoomId = gearDemoRoomId;
	}

	public String getHomeWorldId() {
		return this.homeWorldId;
	}

	public void setHomeWorldId(final String homeWorldId) {
		this.homeWorldId = homeWorldId;
	}

	public String getHubWorldId() {
		return this.hubWorldId;
	}

	public void setHubWorldId(final String hubWorldId) {
		this.hubWorldId = hubWorldId;
	}

	public String getJobsEmail() {
		return this.jobsEmail;
	}

	public void setJobsEmail(final String jobsEmail) {
		this.jobsEmail = jobsEmail;
	}

	public String getMessageOfTheDay() {
		return this.messageOfTheDay;
	}

	public void setMessageOfTheDay(final String messageOfTheDay) {
		this.messageOfTheDay = messageOfTheDay;
	}

	public String getModerationEmail() {
		return this.moderationEmail;
	}

	public void setModerationEmail(final String moderationEmail) {
		this.moderationEmail = moderationEmail;
	}

	public Long getModerationQueryPeriod() {
		return this.moderationQueryPeriod;
	}

	public void setModerationQueryPeriod(final Long moderationQueryPeriod) {
		this.moderationQueryPeriod = moderationQueryPeriod;
	}

	public String getNotAllowedToSelectAvatarInPrivateWorldMessage() {
		return this.notAllowedToSelectAvatarInPrivateWorldMessage;
	}

	public void setNotAllowedToSelectAvatarInPrivateWorldMessage(final String notAllowedToSelectAvatarInPrivateWorldMessage) {
		this.notAllowedToSelectAvatarInPrivateWorldMessage = notAllowedToSelectAvatarInPrivateWorldMessage;
	}

	public String getPlugin() {
		return this.plugin;
	}

	public void setPlugin(final String plugin) {
		this.plugin = plugin;
	}

	public String getReleaseAppVersionStandalone() {
		return this.releaseAppVersionStandalone;
	}

	public void setReleaseAppVersionStandalone(final String releaseAppVersionStandalone) {
		this.releaseAppVersionStandalone = releaseAppVersionStandalone;
	}

	public String getReleaseSdkUrl() {
		return this.releaseSdkUrl;
	}

	public void setReleaseSdkUrl(final String releaseSdkUrl) {
		this.releaseSdkUrl = releaseSdkUrl;
	}

	public String getReleaseSdkVersion() {
		return this.releaseSdkVersion;
	}

	public void setReleaseSdkVersion(final String releaseSdkVersion) {
		this.releaseSdkVersion = releaseSdkVersion;
	}

	public String getReleaseServerVersionStandalone() {
		return this.releaseServerVersionStandalone;
	}

	public void setReleaseServerVersionStandalone(final String releaseServerVersionStandalone) {
		this.releaseServerVersionStandalone = releaseServerVersionStandalone;
	}

	public String getSdkDeveloperFaqUrl() {
		return this.sdkDeveloperFaqUrl;
	}

	public void setSdkDeveloperFaqUrl(final String sdkDeveloperFaqUrl) {
		this.sdkDeveloperFaqUrl = sdkDeveloperFaqUrl;
	}

	public String getSdkDiscordUrl() {
		return this.sdkDiscordUrl;
	}

	public void setSdkDiscordUrl(final String sdkDiscordUrl) {
		this.sdkDiscordUrl = sdkDiscordUrl;
	}

	public String getSdkNotAllowedToPublishMessage() {
		return this.sdkNotAllowedToPublishMessage;
	}

	public void setSdkNotAllowedToPublishMessage(final String sdkNotAllowedToPublishMessage) {
		this.sdkNotAllowedToPublishMessage = sdkNotAllowedToPublishMessage;
	}

	public String getSdkUnityVersion() {
		return this.sdkUnityVersion;
	}

	public void setSdkUnityVersion(final String sdkUnityVersion) {
		this.sdkUnityVersion = sdkUnityVersion;
	}

	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(final String serverName) {
		this.serverName = serverName;
	}

	public String getSupportEmail() {
		return this.supportEmail;
	}

	public void setSupportEmail(final String supportEmail) {
		this.supportEmail = supportEmail;
	}

	public String getTimeOutWorldId() {
		return this.timeOutWorldId;
	}

	public void setTimeOutWorldId(final String timeOutWorldId) {
		this.timeOutWorldId = timeOutWorldId;
	}

	public String getTutorialWorldId() {
		return this.tutorialWorldId;
	}

	public void setTutorialWorldId(final String tutorialWorldId) {
		this.tutorialWorldId = tutorialWorldId;
	}

	public Boolean getUseReliableUdpForVoice() {
		return this.useReliableUdpForVoice;
	}

	public void setUseReliableUdpForVoice(final Boolean useReliableUdpForVoice) {
		this.useReliableUdpForVoice = useReliableUdpForVoice;
	}

	public Long getUserUpdatePeriod() {
		return this.userUpdatePeriod;
	}

	public void setUserUpdatePeriod(final Long userUpdatePeriod) {
		this.userUpdatePeriod = userUpdatePeriod;
	}

	public Long getUpdateRateMsMaximum() {
		return this.updateRateMsMaximum;
	}

	public void setUpdateRateMsMaximum(final Long updateRateMsMaximum) {
		this.updateRateMsMaximum = updateRateMsMaximum;
	}

	public Long getUpdateRateMsMinimum() {
		return this.updateRateMsMinimum;
	}

	public void setUpdateRateMsMinimum(final Long updateRateMsMinimum) {
		this.updateRateMsMinimum = updateRateMsMinimum;
	}

	public Long getUpdateRateMsNormal() {
		return this.updateRateMsNormal;
	}

	public void setUpdateRateMsNormal(final Long updateRateMsNormal) {
		this.updateRateMsNormal = updateRateMsNormal;
	}

	public String getUploadAnalysisPercent() {
		return this.uploadAnalysisPercent;
	}

	public void setUploadAnalysisPercent(final String uploadAnalysisPercent) {
		this.uploadAnalysisPercent = uploadAnalysisPercent;
	}

	public Long getUserVerificationDelay() {
		return this.userVerificationDelay;
	}

	public void setUserVerificationDelay(final Long userVerificationDelay) {
		this.userVerificationDelay = userVerificationDelay;
	}

	public Long getUserVerificationRetry() {
		return this.userVerificationRetry;
	}

	public void setUserVerificationRetry(final Long userVerificationRetry) {
		this.userVerificationRetry = userVerificationRetry;
	}

	public Long getUserVerificationTimeout() {
		return this.userVerificationTimeout;
	}

	public void setUserVerificationTimeout(final Long userVerificationTimeout) {
		this.userVerificationTimeout = userVerificationTimeout;
	}

	public String getViveWindowsUrl() {
		return this.viveWindowsUrl;
	}

	public void setViveWindowsUrl(final String viveWindowsUrl) {
		this.viveWindowsUrl = viveWindowsUrl;
	}

	public List<String> getWhiteListedAssetUrls() {
		return this.whiteListedAssetUrls;
	}

	public void setWhiteListedAssetUrls(final List<String> whiteListedAssetUrls) {
		this.whiteListedAssetUrls = whiteListedAssetUrls;
	}

	public Long getWorldUpdatePeriod() {
		return this.worldUpdatePeriod;
	}

	public void setWorldUpdatePeriod(final Long worldUpdatePeriod) {
		this.worldUpdatePeriod = worldUpdatePeriod;
	}

	public String getYoutubeDlHash() {
		return this.youtubeDlHash;
	}

	public void setYoutubeDlHash(final String youtubeDlHash) {
		this.youtubeDlHash = youtubeDlHash;
	}

	public String getYoutubeDlVersion() {
		return this.youtubeDlVersion;
	}

	public void setYoutubeDlVersion(final String youtubeDlVersion) {
		this.youtubeDlVersion = youtubeDlVersion;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RemoteConfigDto [");
		sb.append("address='").append(this.address).append('\'');
		sb.append(", apiKey='").append(this.apiKey).append('\'');
		sb.append(", appName='").append(this.appName).append('\'');
		sb.append(", buildVersionTag='").append(this.buildVersionTag).append('\'');
		sb.append(", clientApiKey='").append(this.clientApiKey).append('\'');
		sb.append(", clientBPSCeiling=").append(this.clientBPSCeiling);
		sb.append(", clientReservedPlayerBPS=").append(this.clientReservedPlayerBPS);
		sb.append(", clientSentCountAllowance=").append(this.clientSentCountAllowance);
		sb.append(", contactEmail='").append(this.contactEmail).append('\'');
		sb.append(", copyrightEmail='").append(this.copyrightEmail).append('\'');
		sb.append(", currentTOSVersion=").append(this.currentTOSVersion);
		sb.append(", defaultAvatar='").append(this.defaultAvatar).append('\'');
		sb.append(", deploymentGroup='").append(this.deploymentGroup).append('\'');
		sb.append(", devAppVersionStandalone='").append(this.devAppVersionStandalone).append('\'');
		sb.append(", devDownloadLinkWindows='").append(this.devDownloadLinkWindows).append('\'');
		sb.append(", devSdkUrl='").append(this.devSdkUrl).append('\'');
		sb.append(", devSdkVersion='").append(this.devSdkVersion).append('\'');
		sb.append(", devServerVersionStandalone='").append(this.devServerVersionStandalone).append('\'');
		sb.append(", disableAvatarGating=").append(this.disableAvatarGating);
		sb.append(", disableCommunityLabs=").append(this.disableCommunityLabs);
		sb.append(", disableCommunityLabsPromotion=").append(this.disableCommunityLabsPromotion);
		sb.append(", disableEmail=").append(this.disableEmail);
		sb.append(", disableEventStream=").append(this.disableEventStream);
		sb.append(", disableFeedbackGating=").append(this.disableFeedbackGating);
		sb.append(", disableHello=").append(this.disableHello);
		sb.append(", disableRegistration=").append(this.disableRegistration);
		sb.append(", disableSteamNetworking=").append(this.disableSteamNetworking);
		sb.append(", disableTwoFactorAuth=").append(this.disableTwoFactorAuth);
		sb.append(", disableUdon=").append(this.disableUdon);
		sb.append(", disableUpgradeAccount=").append(this.disableUpgradeAccount);
		sb.append(", downloadLinkWindows='").append(this.downloadLinkWindows).append('\'');
		sb.append(", downloadUrls=").append(this.downloadUrls);
		sb.append(", dynamicWorldRows=").append(this.dynamicWorldRows);
		sb.append(", events=").append(this.events);
		sb.append(", gearDemoRoomId='").append(this.gearDemoRoomId).append('\'');
		sb.append(", homeWorldId='").append(this.homeWorldId).append('\'');
		sb.append(", hubWorldId='").append(this.hubWorldId).append('\'');
		sb.append(", jobsEmail='").append(this.jobsEmail).append('\'');
		sb.append(", messageOfTheDay='").append(this.messageOfTheDay).append('\'');
		sb.append(", moderationEmail='").append(this.moderationEmail).append('\'');
		sb.append(", moderationQueryPeriod=").append(this.moderationQueryPeriod);
		sb.append(", notAllowedToSelectAvatarInPrivateWorldMessage='").append(this.notAllowedToSelectAvatarInPrivateWorldMessage).append('\'');
		sb.append(", plugin='").append(this.plugin).append('\'');
		sb.append(", releaseAppVersionStandalone='").append(this.releaseAppVersionStandalone).append('\'');
		sb.append(", releaseSdkUrl='").append(this.releaseSdkUrl).append('\'');
		sb.append(", releaseSdkVersion='").append(this.releaseSdkVersion).append('\'');
		sb.append(", releaseServerVersionStandalone='").append(this.releaseServerVersionStandalone).append('\'');
		sb.append(", sdkDeveloperFaqUrl='").append(this.sdkDeveloperFaqUrl).append('\'');
		sb.append(", sdkDiscordUrl='").append(this.sdkDiscordUrl).append('\'');
		sb.append(", sdkNotAllowedToPublishMessage='").append(this.sdkNotAllowedToPublishMessage).append('\'');
		sb.append(", sdkUnityVersion='").append(this.sdkUnityVersion).append('\'');
		sb.append(", serverName='").append(this.serverName).append('\'');
		sb.append(", supportEmail='").append(this.supportEmail).append('\'');
		sb.append(", timeOutWorldId='").append(this.timeOutWorldId).append('\'');
		sb.append(", tutorialWorldId='").append(this.tutorialWorldId).append('\'');
		sb.append(", useReliableUdpForVoice=").append(this.useReliableUdpForVoice);
		sb.append(", userUpdatePeriod=").append(this.userUpdatePeriod);
		sb.append(", updateRateMsMaximum=").append(this.updateRateMsMaximum);
		sb.append(", updateRateMsMinimum=").append(this.updateRateMsMinimum);
		sb.append(", updateRateMsNormal=").append(this.updateRateMsNormal);
		sb.append(", uploadAnalysisPercent='").append(this.uploadAnalysisPercent).append('\'');
		sb.append(", userVerificationDelay=").append(this.userVerificationDelay);
		sb.append(", userVerificationRetry=").append(this.userVerificationRetry);
		sb.append(", userVerificationTimeout=").append(this.userVerificationTimeout);
		sb.append(", viveWindowsUrl='").append(this.viveWindowsUrl).append('\'');
		sb.append(", whiteListedAssetUrls=").append(this.whiteListedAssetUrls);
		sb.append(", worldUpdatePeriod=").append(this.worldUpdatePeriod);
		sb.append(", youtubeDlHash='").append(this.youtubeDlHash).append('\'');
		sb.append(", youtubeDlVersion='").append(this.youtubeDlVersion).append('\'');
		sb.append(']');
		return sb.toString();
	}

}
