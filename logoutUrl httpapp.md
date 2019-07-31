## header:

- X-Location:
- X-Client-Version:5.6.1
- X-Client-Agent:Xiaomi_Redmi Note 3_5.0.2
- X-IMSI:46001
- X-Platform-Version:5.0.2
- X-User-ID:
- X-Platform-Type:2 
- X-Client-ID:869677025167391 (*#06#)

http://app.pearvideo.com/clt/jsp/v4/domainList.jsp

---
- logout

  Url: http://app.pearvideo.com/clt/v4/logout.msp

  Method: POST

  Cookie: JSESSIONID=44270CE30DFA30FBBF3550B183BD8AFA; __ads_session=cQ9fJiR2UAkPgfmjOwA=; PEAR_PLATFORM=1; PEAR_TOKEN=445639f4-0827-4af2-813c-b460aa694b12; PEAR_UID="igitIi2R93RszBALxaJ3Bg=="; PEAR_UUID=7666DC36-43F2-4871-BC32-78A8242B8AB3; PV_APP=srv-pv-prod-portal3

  Input: 

  Output: 

  ```json
  {
  	"reqId": "9a980ffc-2b6a-494b-9f59-e4d3246bbaa4",
  	"resultMsg": "SUCCESS",
  	"systemTime": 1562680713030,
  	"resultCode": "1"
  }
  ```

  ---

- userHome

  Url: http://app.pearvideo.com/clt/jsp/v4/userHome.jsp

  Method: POST

  Cookie: JSESSIONID=C4AB53AE2A12646B2ED1F96DF0EF50AB; __ads_session=tUqdDSd2UAnCj5SlFgA=; PEAR_PLATFORM=1; PEAR_UUID=7666DC36-43F2-4871-BC32-78A8242B8AB3; PV_APP=srv-pv-prod-portal3

  Input:userId=13504593

  Output:

  ```json
     {
     	"resultCode": "1",
     	"resultMsg": "success",
     	"reqId": "f1360e11-b67e-45a4-b6c7-4ffa88770770",
     	"systemTime": "1562680713222",
     	"userInfo": {
     		"userId": "13504593",
     		"nickname": "小梨子_5892",
     		"signature": "一二三四五上山",
     		"pic": "http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg",
     		"isFollow": "0",
     		"level": "0",
     		"backgroundImg": "http://image.pearvideo.com/const/author_bg.png",
     		"hasAlbum": "0",
     		"shareInfo": {
     			"url": "https://www.pearvideo.com/author_13504593",
     			"title": "小梨子_5892",
     			"summary": "一二三四五上山",
     			"logo": "http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg"
     		}
     	},
     	"nextUrl": "",
     	"dataList": []
     }
  ```

---

- getNewsList(首页-万象)

  Url: http://app.pearvideo.com/clt/jsp/v4/getNewsList.jsp

  Method: GET

  Input: filterIds=100001320,100001318,100001319,100001315,100001316,100001314&start=10&pstart=4 (分页，第一页不加入参)

  Output: 

  ```json
  {
  	"resultCode": "1",
  	"resultMsg": "success",
  	"reqId": "a613ea67-56ec-44fb-91d5-24d4c5b36615",
  	"systemTime": "1562680713222",
  
  	"nextUrl": "http://app.pearvideo.com/clt/jsp/v4/getNewsList.jsp?filterIds=100001320,100001318,100001319,100001315,100001316,100001314&start=10&pstart=4",
  	"dataList": [{
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001320",
  			"name": "初心为民 习近平与一辆二八自行车的故事",
  			"summary": "“一鞭晓色渡滹沱，芳草茸茸漫碧波。”静静流淌的滹沱河水，无言地诉说着千年沧桑。",
  			"pic": "http://image2.pearvideo.com/news/news-100001320-12030626.jpg",
  			"authorName": "央视网",
  			"cardType": "1",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "http://news.cctv.com/2019/07/09/ARTI8qGKAEosciCxkF6NQHqs190709.shtml",
  			"cornerLabel": "1",
  			"cornerLabelDesc": "置顶",
  			"praiseTimes": "0",
  			"sharePic": "http://image.pearvideo.com/news/news-100001320-12030627.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001320"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001318",
  			"name": "原创微视频 | 习近平纵论互联网",
  			"summary": "第十八届中国互联网大会将于7月9日至11日在北京举行。党的十八大以来，习近平总书记就互联网问题发表了很多重要论述。",
  			"pic": "http://image1.pearvideo.com/news/news-100001318-12029998.jpg@!320x180",
  			"authorName": "央视新闻客户端",
  			"cardType": "2",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "http://m.news.cctv.com/2019/07/08/ARTI331TvcuC7hCyDYlZGEXr190708.shtml",
  			"cornerLabel": "1",
  			"cornerLabelDesc": "置顶",
  			"praiseTimes": "92",
  			"sharePic": "http://image2.pearvideo.com/news/news-100001318-12029999.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001318"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001319",
  			"name": "巩固机构改革成果，开创全面深化改革新局面",
  			"summary": "习近平总书记在深化党和国家机构改革总结会议上发表的重要讲话，在社会各界引发强烈反响。\r\n\r\n",
  			"pic": "http://image1.pearvideo.com/news/news-100001319-12030001.jpg@!320x180",
  			"authorName": "央视网",
  			"cardType": "2",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "http://news.cctv.com/2019/07/08/ARTIgQHviPwf1GsVdLFC9jYH190708.shtml",
  			"cornerLabel": "1",
  			"cornerLabelDesc": "置顶",
  			"praiseTimes": "0",
  			"sharePic": "http://image2.pearvideo.com/news/news-100001319-12030002.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001319"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001315",
  			"name": "一图“数”说：深化党和国家机构改革成果",
  			"summary": "7月5日，中共中央总书记、国家主席、中央军委主席习近平在北京出席深化党和国家机构改革总结会议并发表重要讲话。",
  			"pic": "http://image.pearvideo.com/news/news-100001315-12027999.jpg@!320x180",
  			"authorName": "新华社",
  			"cardType": "2",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "http://xhpfmapi.zhongguowangshi.com/vh512/share/6315775?channel=weixin",
  			"cornerLabel": "1",
  			"cornerLabelDesc": "置顶",
  			"praiseTimes": "482",
  			"sharePic": "http://image1.pearvideo.com/news/news-100001315-12028000.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001315"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001316",
  			"name": "习近平讲述的故事 | 英雄母亲邓玉芬",
  			"summary": "五年前的7月7日，习近平总书记讲述了一位英雄母亲的故事。这位母亲把丈夫和5个孩子送上前线，这些亲人全部战死沙场。",
  			"pic": "http://image.pearvideo.com/news/news-100001316-12028002.jpg@!320x180",
  			"authorName": "新华网",
  			"cardType": "2",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "http://xhpfmapi.zhongguowangshi.com/vh512/share/6316002?channel=weixin&from=singlemessage",
  			"cornerLabel": "1",
  			"cornerLabelDesc": "置顶",
  			"praiseTimes": "6",
  			"sharePic": "http://image1.pearvideo.com/news/news-100001316-12028003.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001316"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001314",
  			"name": "以习近平同志为核心的党中央推进党和国家机构改革纪实",
  			"summary": "深化党和国家机构改革总结会议日前在京召开，习近平总书记出席会议并发表重要讲话，对这项事关全局的重大改革作出深刻总结。",
  			"pic": "http://image.pearvideo.com/news/news-100001314-12027597.jpg@!320x180",
  			"authorName": "新华网",
  			"cardType": "2",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "https://app.pearvideo.com/clt/page/v4/news.jsp?newsId=100001314",
  			"cornerLabel": "1",
  			"cornerLabelDesc": "置顶",
  			"praiseTimes": "64",
  			"sharePic": "http://image1.pearvideo.com/news/news-100001314-12027598.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001314"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001317",
  			"name": "外交习语|7月第一周，习主席3场外事活动传递哪些信息",
  			"summary": "5天3场外事活动，展现着习近平主席的忙碌，也传递着中国外交的进取。通过会谈，既推动了中国与三国双边关系的进一步发展，也传递出多重积极信息。",
  			"pic": "http://image.pearvideo.com/news/news-100001317-12028392.png",
  			"authorName": "新华社",
  			"cardType": "1",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "http://xhpfmapi.zhongguowangshi.com/vh512/share/6315868?channel=weixin&from=singlemessage",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"praiseTimes": "57",
  			"sharePic": "http://image1.pearvideo.com/news/news-100001317-12028393.png",
  			"shareUrl": "https://www.pearvideo.com/news_100001317"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001313",
  			"name": "深化党和国家机构改革总结会议召开，习近平发表重要讲话",
  			"summary": "深化党和国家机构改革总结会议5日在北京召开。中共中央总书记、国家主席、中央军委主席习近平出席会议并发表重要讲话。",
  			"pic": "http://image.pearvideo.com/news/news-100001313-12026853.jpg@!320x180",
  			"authorName": "新华网",
  			"cardType": "2",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "https://app.pearvideo.com/clt/page/v4/news.jsp?newsId=100001313",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"praiseTimes": "146",
  			"sharePic": "http://image1.pearvideo.com/news/news-100001313-12026854.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001313"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001312",
  			"name": "习近平对这个问题连说“重话”",
  			"summary": "在“不忘初心、牢记使命”主题教育工作会议上，习近平强调力戒形式主义、官僚主义。新华社原创品牌栏目“讲习所”今天推出文章，为您解读。",
  			"pic": "http://image2.pearvideo.com/news/news-100001312-12026375.jpg@!320x180",
  			"authorName": "新华社",
  			"cardType": "2",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "https://app.pearvideo.com/clt/page/v4/news.jsp?newsId=100001312",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"praiseTimes": "0",
  			"sharePic": "http://image.pearvideo.com/news/news-100001312-12026376.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001312"
  		}
  	}, {
  		"otype": "2",
  		"newsInfo": {
  			"newsId": "100001310",
  			"name": "一篮杨梅里的初心",
  			"summary": "每年六月，福建长汀的杨梅就熟了。此时如果你走进杨梅园，微风摇曳中，一阵阵甜香扑鼻而来。",
  			"pic": "http://image.pearvideo.com/news/news-100001310-12025281.jpg@!320x180",
  			"authorName": "央视网",
  			"cardType": "2",
  			"adExpMonitorUrl": "",
  			"postId": "",
  			"commentTimes": "0",
  			"link": "http://news.cctv.com/2019/07/04/ARTIF52wrNXdxkXpjQYgWUp7190704.shtml",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"praiseTimes": "172",
  			"sharePic": "http://image1.pearvideo.com/news/news-100001310-12025282.jpg",
  			"shareUrl": "https://www.pearvideo.com/news_100001310"
  		}
  	}]
  }
  ```

---

- getVerCode(获取验证码)

  Url: http://app.pearvideo.com/clt/v4/getVerCode.msp

  Method: POST

  Cookie: JSESSIONID=C4AB53AE2A12646B2ED1F96DF0EF50AB; __ads_session=tUqdDSd2UAnCj5SlFgA=; PEAR_PLATFORM=1; PEAR_UUID=7666DC36-43F2-4871-BC32-78A8242B8AB3; PV_APP=srv-pv-prod-portal3

  Input: loginName=13815865892&verType=5

  Output:

  ```json
  {
  	"reqId": "db50fe03-7b95-4d44-ad0a-b4a8ebc27eda",
  	"resultMsg": "SUCCESS",
  	"systemTime": 1562681390100,
  	"resultCode": "1",
  	"verType": 5,
  	"loginName": "13815865892"
  }
  ```

---

- checkVerCode

  Url: http://app.pearvideo.com/clt/v4/checkVerCode.msp

  Method: POST 保持连接

  Cookie: JSESSIONID=C4AB53AE2A12646B2ED1F96DF0EF50AB; __ads_session=F6fZUWt2UAmlM5elAQA=; PEAR_PLATFORM=1; PEAR_UUID=7666DC36-43F2-4871-BC32-78A8242B8AB3; PV_APP=srv-pv-prod-portal3

  Input: loginName=13815865892&verCode=451955&verType=5

  Output:

  ```json
  {
  	"reqId": "7e4074ba-993e-447d-b5b8-cc214848faf4",
  	"resultMsg": "校验成功",
  	"systemTime": 1562681399602,
  	"token": "2096cfff-b194-430e-9794-f1da7f2a6fc3",
  	"resultCode": "1",
  	"verCode": "451955",
  	"userInfo": {
  		"address": null,
  		"appType": 0,
  		"area": null,
  		"biankeType": 0,
  		"birthday": null,
  		"forbidSetHead": 0,
  		"id": 13504593,
  		"illegalHeadTimes": 0,
  		"images": [{
  			"bucket": "pv-prod-img-ugc",
  			"description": null,
  			"filePath": "user\/20190619\/13504593-215406.jpg",
  			"fileSize": 2242,
  			"fileType": "jpg",
  			"height": 132,
  			"id": 15066778,
  			"name": null,
  			"objectId": 13504593,
  			"objectType": 9,
  			"permission": 2,
  			"status": 1,
  			"tag": "head",
  			"updateTime": null,
  			"url": null,
  			"width": 132
  		}],
  		"interestJson": null,
  		"isAuth": 0,
  		"isAuthor": 0,
  		"isBianke": 0,
  		"isBiankeAgreement": 0,
  		"isBlackList": 0,
  		"isIllegalHead": 0,
  		"isPaike": 0,
  		"isPaikeAgreement": 0,
  		"isSetInterest": 0,
  		"isSetPwd": 1,
  		"lastDeviceId": 24651437,
  		"lastLoginIp": "112.86.250.9",
  		"lastLoginTime": "2019-07-09T22:09:59",
  		"level": 0,
  		"mail": null,
  		"mainId": null,
  		"mainStatus": null,
  		"managerId": null,
  		"mobile": "13815865892",
  		"nickname": "小梨子_5892",
  		"nodeId": null,
  		"paikeType": 0,
  		"password": null,
  		"pic": "http:\/\/imageugc.pearvideo.com\/user\/20190619\/13504593-215406.jpg",
  		"regIp": "117.136.45.155",
  		"regTime": "2019-04-26T16:25:31",
  		"schema": "http",
  		"sex": 2,
  		"signature": "一二三四五上山",
  		"societyIdMap": {
  			"WEIXIN": "ovRaPwGdgLMFAMS3PbdE1ahFw0y4"
  		},
  		"token": "2096cfff-b194-430e-9794-f1da7f2a6fc3",
  		"userId": 13504593,
  		"userType": "station",
  		"youngModeEnable": 0,
  		"zenId": null
  	},
  	"verType": 5,
  	"loginName": "13815865892"
  }
  ```

---

- userInfo

  Url: http://app.pearvideo.com/clt/jsp/v4/userInfo.jsp?userId=13504593

  Method: GET

  Cookie: JSESSIONID=C4AB53AE2A12646B2ED1F96DF0EF50AB; PEAR_TOKEN=2096cfff-b194-430e-9794-f1da7f2a6fc3; PEAR_UID="igitIi2R93RszBALxaJ3Bg=="; __ads_session=F6fZUWt2UAmlM5elAQA=; PEAR_PLATFORM=1; PEAR_UUID=7666DC36-43F2-4871-BC32-78A8242B8AB3; PV_APP=srv-pv-prod-portal3

  Input: userId=xxxx

  Output:

  ```json
  {
  	"resultCode": "1",
  	"resultMsg": "success",
  	"reqId": "1d06a3b0-bdf0-4ef1-bd20-a8b4fe44e8a4",
  	"systemTime": "1562681401092",
  	"userInfo": {
  		"userId": "13504593",
  		"nickname": "小梨子_5892",
  		"signature": "一二三四五上山",
  		"pic": "http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg",
  		"isFollow": "0",
  		"level": "0",
  		"backgroundImg": "http://image.pearvideo.com/const/author_bg.png",
  		"hasAlbum": "0",
  		"shareInfo": {
  			"url": "https://www.pearvideo.com/author_13504593",
  			"title": "小梨子_5892",
  			"summary": "一二三四五上山",
  			"logo": "http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg"
  		}
  	}
  }
  ```

---

- home(首页-推荐)

  Url: http://app.pearvideo.com/clt/jsp/v4/home.jsp

  Method: POST

  Cookie:

  Input: isHome=1     isHome=1&channelCode=&start=10

  Output: 

  ```json
  {
  	"resultCode": "1",
  	"resultMsg": "success",
  	"reqId": "cf85ed8d-73fd-474f-8d6a-d812995412ec",
  	"systemTime": "1562682411913",
  	"nextUrl": "http://app.pearvideo.com/clt/jsp/v4/home.jsp?isHome=1&channelCode=&start=10",
  	"areaList": [{
  		"area_id": "100001",
  		"expInfo": {
  			"algorighm_exp_id": "",
  			"front_exp_id": "0",
  			"s_value": "cf85ed8d-73fd-474f-8d6a-d812995412ec_21642206468203450"
  		}
  	}, {
  		"area_id": "100042",
  		"expInfo": {
  			"algorighm_exp_id": "",
  			"front_exp_id": "0",
  			"s_value": "cf85ed8d-73fd-474f-8d6a-d812995412ec_21642206468203450"
  		}
  	}],
  	"dataList": [{
  		"nodeType": "1",
  		"nodeName": "头条",
  		"contList": [{
  			"contId": "1574983",
  			"name": "91年小伙酒驾自称孩子，93年交警懵",
  			"pic": "http://image2.pearvideo.com/cont/20190709/cont-1574983-12032033.png",
  			"userInfo": {
  				"userId": "11580045",
  				"nickname": "涵涵",
  				"pic": "http://imageugc.pearvideo.com/user/20171226/11580045-150455-864684038235699.jpg",
  				"level": "1"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "3",
  			"cornerLabelDesc": "独播",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'01\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "784",
  			"pv": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,河北省,廊坊市,广阳区",
  				"showName": "拍客涵涵·发自河北省廊坊市",
  				"address": "河北省廊坊市广阳区万庄服务区(京台高速公路西)",
  				"loc": "116.50233,39.542875|中国,河北省,廊坊市,广阳区",
  				"placeName": "万庄服务区",
  				"longitude": 116.50233,
  				"latitude": 39.542875
  			},
  			"coverVideo": "http://video.pearvideo.com/head/20190709/cont-1574983-14110185.mp4",
  			"tagInfo": {}
  		}, {
  			"contId": "1575335",
  			"name": "退伍兵KO小偷走红:小偷太背,别模仿",
  			"pic": "http://image1.pearvideo.com/cont/20190709/cont-1575335-12031909.jpg",
  			"userInfo": {
  				"userId": "10136488",
  				"nickname": "随手拍身边",
  				"pic": "http://imageugc.pearvideo.com/user/20190420/10136488-080214-865925044158212.jpg",
  				"level": "1"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "3",
  			"cornerLabelDesc": "独播",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'39\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "885",
  			"pv": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,山东省,滨州市,滨城区",
  				"showName": "拍客随手拍身边·发自山东省滨州市",
  				"address": "",
  				"loc": "0.0,0.0|中国,山东省,滨州市,滨城区",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"coverVideo": "http://video.pearvideo.com/head/20190709/cont-1575335-14109848.mp4",
  			"tagInfo": {}
  		}, {
  			"contId": "1575005",
  			"name": "41人回校，重温20年前写给自己的信",
  			"pic": "http://image2.pearvideo.com/cont/20190709/cont-1575005-12031715.png",
  			"userInfo": {
  				"userId": "10161905",
  				"nickname": "梨山东",
  				"pic": "http://imageugc.pearvideo.com/user/20170911/10161905-163843.jpg",
  				"level": "1"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'09\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "768",
  			"pv": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,山东省,临沂市,沂水县",
  				"showName": "拍客梨山东·发自山东省临沂市",
  				"address": "山东省临沂市沂水县马站镇委(振兴路北)",
  				"loc": "118.758804,36.066748|中国,山东省,临沂市,沂水县",
  				"placeName": "马站镇",
  				"longitude": 118.758804,
  				"latitude": 36.066748
  			},
  			"coverVideo": "http://video.pearvideo.com/head/20190709/cont-1575005-14109350.mp4",
  			"tagInfo": {}
  		}, {
  			"contId": "1574905",
  			"name": "现实蜘蛛侠？神秘人徒手爬英最高楼",
  			"pic": "http://image1.pearvideo.com/cont/20190709/cont-1574905-12031504.png",
  			"userInfo": {
  				"userId": "11549111",
  				"nickname": "OMG!",
  				"pic": "http://image.pearvideo.com/node/35-10030502-logo.jpg",
  				"level": "2"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "00'36\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "224",
  			"pv": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "英国",
  				"showName": "",
  				"address": "",
  				"loc": "0.0,0.0|英国",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"coverVideo": "http://video.pearvideo.com/head/20190709/cont-1574905-14108750.mp4",
  			"tagInfo": {}
  		}, {
  			"contId": "1575234",
  			"name": "瓜农2千个西瓜被砍?警方:实为650个",
  			"pic": "http://image.pearvideo.com/cont/20190709/cont-1575234-12031272.png",
  			"userInfo": {
  				"userId": "11902109",
  				"nickname": "草媒视界",
  				"pic": "http://imageugc.pearvideo.com/user/20180418/11902109-081614-865334038814072.jpg",
  				"level": "1"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "3",
  			"cornerLabelDesc": "独播",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'28\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "749",
  			"pv": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,陕西省,西安市,蓝田县",
  				"showName": "拍客草媒视界·发自陕西省西安市",
  				"address": "蓝田县",
  				"loc": "0.0,0.0|中国,陕西省,西安市,蓝田县",
  				"placeName": "蓝田县",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"coverVideo": "http://video.pearvideo.com/head/20190709/cont-1575234-14108185.mp4",
  			"tagInfo": {}
  		}],
  		"dayHotInfo": {
  			"barName": "24H热门",
  			"unreadNum": "31",
  			"lastUpdateTime": "22:00",
  			"contList": [{
  				"contId": "1575176",
  				"name": "95后制外挂牟利200万：只有我会做",
  				"cornerLabel": "3",
  				"cornerLabelDesc": "独播"
  			}, {
  				"contId": "1574555",
  				"name": "环卫工创爱马仕炒饭，月收入二十万",
  				"cornerLabel": "3",
  				"cornerLabelDesc": "独播"
  			}, {
  				"contId": "1575262",
  				"name": "韩男子暴打越南妻：其他男人也这样",
  				"cornerLabel": "3",
  				"cornerLabelDesc": "独播"
  			}]
  		}
  	}, {
  		"nodeType": "11",
  		"nodeName": "",
  		"moreId": "103"
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1565667",
  			"name": "说话太逗！导游陕西话讲兵马俑走红",
  			"pic": "http://image2.pearvideo.com/cont/20190613/cont-1565667-11997032.png",
  			"userInfo": {
  				"userId": "10222236",
  				"nickname": "去现场",
  				"pic": "http://imageugc.pearvideo.com/user/20170207/10222236-154132.jpg",
  				"level": "1",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'33\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1542152&contId=1565667",
  			"postId": "1542152",
  			"commentTimes": "115",
  			"summary": "陕西西安，导游张斌用陕西话讲解兵马俑走红网络。每次在博物馆门口，他都会用陕西话教游客念“秦兵马俑一号坑遗址”。张斌称，这样讲了三四年了，在讲解中融入方言能让游客留下深刻印象，不然就是走马观花看泥娃娃。",
  			"sharePic": "http://image.pearvideo.com/cont/20190613/cont-1565667-11997033.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1565667",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "1095",
  				"name": "不想看：陕西"
  			}, {
  				"tagId": "1187",
  				"name": "不想看：导游"
  			}],
  			"videos": [{
  				"videoId": "14011776",
  				"url": "http://video.pearvideo.com/mp4/short/20190613/cont-1565667-14011763_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "5494774",
  				"duration": "93"
  			}, {
  				"videoId": "14011775",
  				"url": "http://video.pearvideo.com/mp4/short/20190613/cont-1565667-14011763_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "3335969",
  				"duration": "93"
  			}, {
  				"videoId": "14011774",
  				"url": "http://video.pearvideo.com/mp4/short/20190613/cont-1565667-14011763_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "10036482",
  				"duration": "93"
  			}, {
  				"videoId": "14011773",
  				"url": "http://video.pearvideo.com/mp4/short/20190613/cont-1565667-14011763_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "19884070",
  				"duration": "93"
  			}],
  			"praiseTimes": "3063",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,陕西省,西安市,临潼区",
  				"showName": "拍客去现场·发自陕西省西安市",
  				"address": "",
  				"loc": "0.0,0.0|中国,陕西省,西安市,临潼区",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "1",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "4",
  		"nodeName": "我们的70年",
  		"moreId": "100475",
  		"contList": [{
  			"contId": "1561752",
  			"name": "高校600学子组\"70\"方阵:为祖国庆生",
  			"pic": "http://image1.pearvideo.com/cont/20190602/cont-1561752-11984326.png",
  			"userInfo": {
  				"userId": "11549083",
  				"nickname": "梨杭州",
  				"pic": "http://image.pearvideo.com/node/2435-10777838-logo.png",
  				"level": "2"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "3",
  			"cornerLabelDesc": "独播",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "00'59\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "1584",
  			"pv": "0",
  			"adExpMonitorUrl": ""
  		}, {
  			"contId": "1561170",
  			"name": "最牛地理老师!用900学生摆中国地图",
  			"pic": "http://image2.pearvideo.com/cont/20190531/cont-1561170-11982386.png",
  			"userInfo": {
  				"userId": "12191228",
  				"nickname": "马小军",
  				"pic": "http://imageugc.pearvideo.com/user/20181001/12191228-234615.jpg",
  				"level": "1"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "3",
  			"cornerLabelDesc": "独播",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'13\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "2735",
  			"pv": "0",
  			"adExpMonitorUrl": ""
  		}, {
  			"contId": "1562835",
  			"name": "东财万名师生同唱《我和我的祖国》",
  			"pic": "http://image1.pearvideo.com/main/20190605/13236954-160158-1.png",
  			"userInfo": {
  				"userId": "13236954",
  				"nickname": "@雷锋",
  				"pic": "http://image.pearvideo.com/node/3392-11866684-logo.png",
  				"level": "2"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "03'00\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "895",
  			"pv": "0",
  			"adExpMonitorUrl": ""
  		}, {
  			"contId": "1574777",
  			"name": "从小爱英雄,他10年画500张英模画像",
  			"pic": "http://image.pearvideo.com/main/20190708/12487669-180220-1.png",
  			"userInfo": {
  				"userId": "11549102",
  				"nickname": "梨郑州",
  				"pic": "http://image.pearvideo.com/node/2434-10777839-logo.png",
  				"level": "2"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'05\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "46",
  			"pv": "0",
  			"adExpMonitorUrl": ""
  		}]
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1562592",
  			"name": "女主持开餐馆，从96斤猛增到218斤",
  			"pic": "http://image2.pearvideo.com/cont/20190605/cont-1562592-11988434.png",
  			"userInfo": {
  				"userId": "11183716",
  				"nickname": "寻味长春",
  				"pic": "http://imageugc.pearvideo.com/user/20180908/11183716-090011.png",
  				"level": "1",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "0",
  			"cornerLabel": "3",
  			"cornerLabelDesc": "独播",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "02'03\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1539810&contId=1562592",
  			"postId": "1539810",
  			"commentTimes": "12",
  			"summary": "2019年6月3日，吉林长春。32岁的彤彤体重达到218斤，她来到医院做减重手术。彤彤之前是一名主持人，后来经商开餐馆，她说曾为试菜一天吃16顿饭，吃胀之后抠吐继续吃，如今为了能有个健康的宝宝，所以选择切胃减肥。",
  			"sharePic": "http://image.pearvideo.com/cont/20190605/cont-1562592-11988435.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1562592",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "346",
  				"name": "不想看：校园暴力"
  			}, {
  				"tagId": "958",
  				"name": "不想看：学校"
  			}],
  			"videos": [{
  				"videoId": "14071206",
  				"url": "http://video.pearvideo.com/mp4/short/20190629/cont-1562592-14071181_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "33727066",
  				"duration": "123"
  			}, {
  				"videoId": "14071205",
  				"url": "http://video.pearvideo.com/mp4/short/20190629/cont-1562592-14071181_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "5556631",
  				"duration": "123"
  			}, {
  				"videoId": "14071204",
  				"url": "http://video.pearvideo.com/mp4/short/20190629/cont-1562592-14071181_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "17568231",
  				"duration": "123"
  			}, {
  				"videoId": "14071203",
  				"url": "http://video.pearvideo.com/mp4/short/20190629/cont-1562592-14071181_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "9689307",
  				"duration": "123"
  			}],
  			"praiseTimes": "1384",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,吉林省,长春市,绿园区",
  				"showName": "拍客寻味长春·发自吉林省长春市",
  				"address": "",
  				"loc": "0.0,0.0|中国,吉林省,长春市,绿园区",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "1",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1548343",
  			"name": "老汉被医生宣布死亡，灵堂上手动了",
  			"pic": "http://image2.pearvideo.com/cont/20190427/cont-1548343-11941136.png",
  			"userInfo": {
  				"userId": "10811564",
  				"nickname": "梨拍客",
  				"pic": "http://imageugc.pearvideo.com/user/20170722/10811564-224752.jpg",
  				"level": "1",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "0",
  			"cornerLabel": "3",
  			"cornerLabelDesc": "独播",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'04\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1526501&contId=1548343",
  			"postId": "1526501",
  			"commentTimes": "698",
  			"summary": "近日，安徽阜阳，一老汉陷入昏迷呼吸停止，被当地医生宣布死亡，家人将他带回家准备后事，搭建灵堂。在第二天清晨，在亲人的哭声中，老汉的手突然动了一下。家人紧急将他送到医院救治。经5小时的手术，老汉又活了。",
  			"sharePic": "http://image.pearvideo.com/cont/20190427/cont-1548343-11941137.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1548343",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "1089",
  				"name": "不想看：安徽"
  			}, {
  				"tagId": "1191",
  				"name": "不想看：死亡"
  			}],
  			"videos": [{
  				"videoId": "13854589",
  				"url": "http://video.pearvideo.com/mp4/short/20190427/cont-1548343-13854563_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "2844914",
  				"duration": "64"
  			}, {
  				"videoId": "13854588",
  				"url": "http://video.pearvideo.com/mp4/short/20190427/cont-1548343-13854563_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "4824148",
  				"duration": "64"
  			}, {
  				"videoId": "13854587",
  				"url": "http://video.pearvideo.com/mp4/short/20190427/cont-1548343-13854563_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "9490404",
  				"duration": "64"
  			}, {
  				"videoId": "13854586",
  				"url": "http://video.pearvideo.com/mp4/short/20190427/cont-1548343-13854563_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "1827108",
  				"duration": "64"
  			}],
  			"praiseTimes": "1796",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,安徽省,阜阳市,颍上县",
  				"showName": "拍客梨拍客·发自安徽省阜阳市",
  				"address": "",
  				"loc": "0.0,0.0|中国,安徽省,阜阳市,颍上县",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "1",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1556070",
  			"name": "他爱情公交碰运气，遇女同学秒牵手",
  			"pic": "http://image.pearvideo.com/cont/20190518/cont-1556070-11966109.png",
  			"userInfo": {
  				"userId": "11157907",
  				"nickname": "河南梨",
  				"pic": "http://imageugc.pearvideo.com/user/20170925/11157907-103154.jpg",
  				"level": "1",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "00'47\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1533553&contId=1556070",
  			"postId": "1533553",
  			"commentTimes": "42",
  			"summary": "5月16日，河南郑州，云悉驾驶的62路公交车是有名的“爱情专列”，已促成多对情侣。一单身小伙便来碰运气，恰巧在车上遇到多年未见的小学女同学，两人一见钟情。小伙下车前送给司机一瓶水表示感谢。",
  			"sharePic": "http://image1.pearvideo.com/cont/20190518/cont-1556070-11966110.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1556070",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "4783",
  				"name": "不想看：情侣"
  			}, {
  				"tagId": "815",
  				"name": "不想看：公交车"
  			}],
  			"videos": [{
  				"videoId": "13922667",
  				"url": "http://video.pearvideo.com/mp4/short/20190518/cont-1556070-13922658_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "3379143",
  				"duration": "47"
  			}, {
  				"videoId": "13922666",
  				"url": "http://video.pearvideo.com/mp4/short/20190518/cont-1556070-13922658_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "6000265",
  				"duration": "47"
  			}, {
  				"videoId": "13922665",
  				"url": "http://video.pearvideo.com/mp4/short/20190518/cont-1556070-13922658_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "11276865",
  				"duration": "47"
  			}, {
  				"videoId": "13922664",
  				"url": "http://video.pearvideo.com/mp4/short/20190518/cont-1556070-13922658_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "21400229",
  				"duration": "47"
  			}],
  			"praiseTimes": "3167",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,河南省,郑州市,中原区",
  				"showName": "拍客河南梨·发自河南省郑州市",
  				"address": "",
  				"loc": "0.0,0.0|中国,河南省,郑州市,中原区",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "1",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1562326",
  			"name": "男子给女生放不雅视频，被路人摁倒",
  			"pic": "http://image2.pearvideo.com/cont/20190604/cont-1562326-11986109.png",
  			"userInfo": {
  				"userId": "10206559",
  				"nickname": "剥洋葱",
  				"pic": "http://imageugc.pearvideo.com/user/20170614/10206559-160527.jpg",
  				"level": "1",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "1",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "00'57\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1539162&contId=1562326",
  			"postId": "1539162",
  			"commentTimes": "28",
  			"summary": "5月31日，山东青岛，一名女子在路边等车时，一名陌生男子走上前，掏出手机给女子播放不雅视频。因感觉遭到侮辱，女子气不过便与其争吵起来。此时，市民张先生路过此地看见这一幕，将男子摁倒在地，并立即报警。",
  			"sharePic": "http://image.pearvideo.com/cont/20190604/cont-1562326-11986110.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1562326",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "1092",
  				"name": "不想看：山东"
  			}, {
  				"tagId": "1115",
  				"name": "不想看：青岛"
  			}],
  			"videos": [{
  				"videoId": "13980124",
  				"url": "http://video.pearvideo.com/mp4/short/20190604/cont-1562326-13980105_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "12298867",
  				"duration": "57"
  			}, {
  				"videoId": "13980123",
  				"url": "http://video.pearvideo.com/mp4/short/20190604/cont-1562326-13980105_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "3918260",
  				"duration": "57"
  			}, {
  				"videoId": "13980122",
  				"url": "http://video.pearvideo.com/mp4/short/20190604/cont-1562326-13980105_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "6615820",
  				"duration": "57"
  			}, {
  				"videoId": "13980121",
  				"url": "http://video.pearvideo.com/mp4/short/20190604/cont-1562326-13980105_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "2482016",
  				"duration": "57"
  			}],
  			"praiseTimes": "1810",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,山东省,青岛市,黄岛区",
  				"showName": "拍客剥洋葱·发自山东省青岛市",
  				"address": "",
  				"loc": "0.0,0.0|中国,山东省,青岛市,黄岛区",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "1",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "4",
  		"nodeName": "老拼呃，老字号新故事",
  		"moreId": "102632",
  		"contList": [{
  			"contId": "1567031",
  			"name": "90后小伙10辆自行车怀旧风迎亲",
  			"pic": "http://image1.pearvideo.com/cont/20190617/cont-1567031-12001612.jpg",
  			"userInfo": {
  				"userId": "11549090",
  				"nickname": "微辣Video",
  				"pic": "http://image.pearvideo.com/node/9-10027910-logo.jpg",
  				"level": "2"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "02'47\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "2145",
  			"pv": "0",
  			"adExpMonitorUrl": ""
  		}, {
  			"contId": "1557817",
  			"name": "法国妹纸花露水当香水：像爱马仕",
  			"pic": "http://image1.pearvideo.com/cont/20190523/cont-1557817-11971876.jpg",
  			"userInfo": {
  				"userId": "11549090",
  				"nickname": "微辣Video",
  				"pic": "http://image.pearvideo.com/node/9-10027910-logo.jpg",
  				"level": "2"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "02'22\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "1942",
  			"pv": "0",
  			"adExpMonitorUrl": ""
  		}, {
  			"contId": "1553596",
  			"name": "90岁奶奶用雪花膏70年肤似少女",
  			"pic": "http://image2.pearvideo.com/cont/20190513/cont-1553596-11958578.jpg",
  			"userInfo": {
  				"userId": "11549090",
  				"nickname": "微辣Video",
  				"pic": "http://image.pearvideo.com/node/9-10027910-logo.jpg",
  				"level": "2"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "02'17\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "2496",
  			"pv": "0",
  			"adExpMonitorUrl": ""
  		}, {
  			"contId": "1552268",
  			"name": "拼多多启动上海老字号新电商计划",
  			"pic": "http://image2.pearvideo.com/cont/20190509/cont-1552268-11954096.jpg",
  			"userInfo": {
  				"userId": "11549090",
  				"nickname": "微辣Video",
  				"pic": "http://image.pearvideo.com/node/9-10027910-logo.jpg",
  				"level": "2"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'26\u0022",
  			"liveStatus": "",
  			"liveStartTime": "",
  			"isAppoint": "0",
  			"praiseTimes": "1213",
  			"pv": "0",
  			"adExpMonitorUrl": ""
  		}]
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1570328",
  			"name": "胖女孩参加健身赛，刚上台就被退场",
  			"pic": "http://image.pearvideo.com/cont/20190625/cont-1570328-12013152.png",
  			"userInfo": {
  				"userId": "12701991",
  				"nickname": "树林哥",
  				"pic": "http://imageugc.pearvideo.com/user/20181211/12701991-152357-869095037655750.jpg",
  				"level": "1",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "0",
  			"cornerLabel": "3",
  			"cornerLabelDesc": "独播",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "00'57\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1546405&contId=1570328",
  			"postId": "1546405",
  			"commentTimes": "75",
  			"summary": "2019年6月22日，山西省健美健身锦标赛现场，一名身材微胖的女生身着比基尼上场，刚站上台就被喊话：请退场。山西省健美健美操协会高先生称，女生是名大学生，不是专业选手，因体脂率超标故不能比赛，但精神可嘉。",
  			"sharePic": "http://image1.pearvideo.com/cont/20190625/cont-1570328-12013153.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1570328",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "899",
  				"name": "不想看：山西"
  			}, {
  				"tagId": "957",
  				"name": "不想看：学生"
  			}],
  			"videos": [{
  				"videoId": "14057363",
  				"url": "http://video.pearvideo.com/mp4/short/20190625/cont-1570328-14057348_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "7334254",
  				"duration": "57"
  			}, {
  				"videoId": "14057362",
  				"url": "http://video.pearvideo.com/mp4/short/20190625/cont-1570328-14057348_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "4101099",
  				"duration": "57"
  			}, {
  				"videoId": "14057361",
  				"url": "http://video.pearvideo.com/mp4/short/20190625/cont-1570328-14057348_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "2501722",
  				"duration": "57"
  			}, {
  				"videoId": "14057360",
  				"url": "http://video.pearvideo.com/mp4/short/20190625/cont-1570328-14057348_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "14753898",
  				"duration": "57"
  			}],
  			"praiseTimes": "1906",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,山西省,太原市,迎泽区",
  				"showName": "拍客树林哥·发自山西省太原市",
  				"address": "",
  				"loc": "0.0,0.0|中国,山西省,太原市,迎泽区",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "1",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1574775",
  			"name": "看完花木兰预告，网友被刘亦菲圈粉",
  			"pic": "http://image1.pearvideo.com/cont/20190708/cont-1574775-12029173.png",
  			"userInfo": {
  				"userId": "11549105",
  				"nickname": "时差视频",
  				"pic": "http://image.pearvideo.com/node/10-10027909-logo.jpg",
  				"level": "2",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "1",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "00'40\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1550434&contId=1574775",
  			"postId": "1550434",
  			"commentTimes": "87",
  			"summary": "7月8日花木兰真人版电影预告首发，影片还原大量动画和历史场景。海外网友对主演刘亦菲的打斗场面一致好评，也吐槽了木须龙没有出现。有人称本来很抗拒花木兰重拍，但冲着预告里刘亦菲舞剑和飘逸头发很想去电影院看。",
  			"sharePic": "http://image2.pearvideo.com/cont/20190708/cont-1574775-12029171.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1574775",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "51668",
  				"name": "不想看：冲着"
  			}, {
  				"tagId": "7612",
  				"name": "不想看：影片"
  			}],
  			"videos": [{
  				"videoId": "14102106",
  				"url": "http://video.pearvideo.com/mp4/short/20190708/cont-1574775-14102062_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "1758123",
  				"duration": "40"
  			}, {
  				"videoId": "14102105",
  				"url": "http://video.pearvideo.com/mp4/short/20190708/cont-1574775-14102062_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "2859906",
  				"duration": "40"
  			}, {
  				"videoId": "14102104",
  				"url": "http://video.pearvideo.com/mp4/short/20190708/cont-1574775-14102062_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "7701284",
  				"duration": "40"
  			}, {
  				"videoId": "14102103",
  				"url": "http://video.pearvideo.com/mp4/short/20190708/cont-1574775-14102062_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "4771937",
  				"duration": "40"
  			}],
  			"praiseTimes": "629",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "美国",
  				"showName": "发自美国洛杉矶",
  				"loc": "0.0,0.0|美国",
  				"placeName": "洛杉矶",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "0",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "11",
  		"nodeName": "",
  		"moreId": "84"
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1575176",
  			"name": "95后制外挂牟利200万：只有我会做",
  			"pic": "http://image2.pearvideo.com/cont/20190709/cont-1575176-12030926.png",
  			"userInfo": {
  				"userId": "11543905",
  				"nickname": "菏泽频道",
  				"pic": "http://imageugc.pearvideo.com/user/20171216/11543905-192850.jpg",
  				"level": "1",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'26\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1550820&contId=1575176",
  			"postId": "1550820",
  			"commentTimes": "2",
  			"summary": "山东东明，一男子最近玩游戏老输便举报对方用外挂。民警顺藤摸瓜，捣毁了一卖外挂的团伙，抓获16人，涉案上千万元。外挂开发者吴某仅21岁，已非法牟利两百万。2019年7月他向民警表示：“外挂太复杂，只有我会做。”",
  			"sharePic": "http://image1.pearvideo.com/cont/20190709/cont-1575176-12030925.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1575176",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "12286",
  				"name": "不想看：卖家"
  			}, {
  				"tagId": "2798",
  				"name": "不想看：作弊"
  			}],
  			"videos": [{
  				"videoId": "14107388",
  				"url": "http://video.pearvideo.com/mp4/short/20190709/cont-1575176-14107346_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "8140032",
  				"duration": "86"
  			}, {
  				"videoId": "14107387",
  				"url": "http://video.pearvideo.com/mp4/short/20190709/cont-1575176-14107346_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "2579318",
  				"duration": "86"
  			}, {
  				"videoId": "14107386",
  				"url": "http://video.pearvideo.com/mp4/short/20190709/cont-1575176-14107346_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "18029299",
  				"duration": "86"
  			}, {
  				"videoId": "14107385",
  				"url": "http://video.pearvideo.com/mp4/short/20190709/cont-1575176-14107346_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "4269034",
  				"duration": "86"
  			}],
  			"praiseTimes": "598",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,山东省,菏泽市,东明县",
  				"showName": "拍客菏泽频道·发自山东省菏泽市",
  				"address": "",
  				"loc": "0.0,0.0|中国,山东省,菏泽市,东明县",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "1",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1555554",
  			"name": "出门警惕这种虫！夺命蜱虫1月致3死",
  			"pic": "http://image.pearvideo.com/cont/20190517/cont-1555554-11964459.png",
  			"userInfo": {
  				"userId": "10000128",
  				"nickname": "梨安徽",
  				"pic": "http://imageugc.pearvideo.com/user/20171118/10000128-135153.jpg",
  				"level": "1",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "0",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "00'50\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1533098&contId=1555554",
  			"postId": "1533098",
  			"commentTimes": "137",
  			"summary": "安徽合肥，一个多月来，安医大第二附属医院收治的蜱虫叮咬患者中，有3人因感染病毒死亡。医生介绍，新型布尼亚病毒感染之后无特效药，病毒会引发多脏器功能损害，一旦错过最佳治疗期，就会病情加重，甚至导致死亡。",
  			"sharePic": "http://image1.pearvideo.com/cont/20190517/cont-1555554-11964460.png",
  			"shareUrl": "https://www.pearvideo.com/detail_1555554",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "1089",
  				"name": "不想看：安徽"
  			}, {
  				"tagId": "1698",
  				"name": "不想看：合肥"
  			}],
  			"videos": [{
  				"videoId": "13917975",
  				"url": "http://video.pearvideo.com/mp4/short/20190517/cont-1555554-13917967_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "9271982",
  				"duration": "50"
  			}, {
  				"videoId": "13917974",
  				"url": "http://video.pearvideo.com/mp4/short/20190517/cont-1555554-13917967_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "2371186",
  				"duration": "50"
  			}, {
  				"videoId": "13917973",
  				"url": "http://video.pearvideo.com/mp4/short/20190517/cont-1555554-13917967_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "4367806",
  				"duration": "50"
  			}, {
  				"videoId": "13917972",
  				"url": "http://video.pearvideo.com/mp4/short/20190517/cont-1555554-13917967_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "1479313",
  				"duration": "50"
  			}],
  			"praiseTimes": "1957",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "中国,安徽省,合肥市,蜀山区",
  				"showName": "拍客梨安徽·发自安徽省合肥市",
  				"address": "",
  				"loc": "0.0,0.0|中国,安徽省,合肥市,蜀山区",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "1",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}, {
  		"nodeType": "13",
  		"contList": [{
  			"contId": "1575262",
  			"name": "韩男子暴打越南妻：其他男人也这样",
  			"pic": "http://image.pearvideo.com/cont/20190709/cont-1575262-12031266.jpg",
  			"userInfo": {
  				"userId": "11549105",
  				"nickname": "时差视频",
  				"pic": "http://image.pearvideo.com/node/10-10027909-logo.jpg",
  				"level": "2",
  				"isFollow": "0"
  			},
  			"link": "http://",
  			"linkType": "0",
  			"isVr": "0",
  			"aspectRatio": "1",
  			"cornerLabel": "",
  			"cornerLabelDesc": "",
  			"forwordType": "1",
  			"videoType": "1",
  			"duration": "01'55\u0022",
  			"liveStatus": "",
  			"postHtml": "https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1550880&contId=1575262",
  			"postId": "1550880",
  			"commentTimes": "5",
  			"summary": "7月4日，在韩居住的越南籍女性上传遭韩国丈夫暴打的视频，引两国网民强烈愤慨。受害女性称多次遭丈夫殴打，致肋骨骨折，2岁儿子因此患上抑郁症。男子随后被紧急逮捕，称因妻子韩语不好才打人，其他男人应该也一样。",
  			"sharePic": "http://image2.pearvideo.com/cont/20190709/cont-1575262-12031268.jpg",
  			"shareUrl": "https://www.pearvideo.com/detail_1575262",
  			"tags": [{
  				"tagId": "0",
  				"name": "内容质量差"
  			}, {
  				"tagId": "2159",
  				"name": "不想看：家庭暴力"
  			}, {
  				"tagId": "622",
  				"name": "不想看：家暴"
  			}],
  			"videos": [{
  				"videoId": "14108082",
  				"url": "http://video.pearvideo.com/mp4/short/20190709/cont-1575262-14108018_pkg-sd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "sd",
  				"format": "mp4",
  				"fileSize": "5720448",
  				"duration": "115"
  			}, {
  				"videoId": "14108081",
  				"url": "http://video.pearvideo.com/mp4/short/20190709/cont-1575262-14108018_pkg-fhd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "fhd",
  				"format": "mp4",
  				"fileSize": "17806261",
  				"duration": "115"
  			}, {
  				"videoId": "14108080",
  				"url": "http://video.pearvideo.com/mp4/short/20190709/cont-1575262-14108018_pkg-ld.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "ld",
  				"format": "mp4",
  				"fileSize": "3554434",
  				"duration": "115"
  			}, {
  				"videoId": "14108079",
  				"url": "http://video.pearvideo.com/mp4/short/20190709/cont-1575262-14108018_pkg-hd.mp4",
  				"name": "",
  				"desc": "",
  				"tag": "hd",
  				"format": "mp4",
  				"fileSize": "9822596",
  				"duration": "115"
  			}],
  			"praiseTimes": "625",
  			"isFavorited": "0",
  			"adExpMonitorUrl": "",
  			"geo": {
  				"namePath": "韩国",
  				"showName": "",
  				"loc": "0.0,0.0|韩国",
  				"placeName": "",
  				"longitude": 0,
  				"latitude": 0
  			},
  			"isVideoPlus": "0",
  			"isDownload": "0",
  			"adName": "",
  			"adLogo": ""
  		}]
  	}]
  }
  ```

---

- 













Url:

Method:

Cookie:

Input:

Output: