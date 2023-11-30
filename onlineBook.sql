CREATE SCHEMA `online_library` ;
use `online_library`;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` text NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `admin` VALUES (1,'Nguyễn Hữu Tuấn','huutuan1705@gmail.com','huutuan','12345');

CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `author` text NOT NULL,
  `description` text,
  `releaseDate` date NOT NULL,
  `pages` int DEFAULT NULL,
  `categoryID` int DEFAULT NULL,
  `image` text,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `book` VALUES (1,'Thép đã tôi thế đấy','Nikolai A Ostrovsky','\"Thép đã tôi thế đấy\" Được đăng lần dầu trên tạp chí Molodaya Gvardiya vào năm 1932 và được xuất bản thành sách vào năm 1936 . Không phải một tiểu thuyết bình thường , \"Thép đã tôi thế đấy\" chính là cuộc đời của tác giả Nikolai A.Ostrovsky. Bằng sự can trường của người chiến sĩ cách mạng , dù cơ thể bị tàn phá , đau đớn , Nikolai A.Ostrovsky vẫn sống trọn vẹn , cống hiến cho xã hội cuốn tiểu thuyết bất hủ \"Thép đã tôi thế đấy\" và thành công trong xây dựng hình tượng nhân vật chính - người chiến sĩ hồng quân Pavel Korchagin . Nhiệt tình cách mạng nồng cháy của Pavel đã khiến nhiều độc giả yêu quý anh và phương châm sống của anh cũng đã trở thành phương châm sống của nhiều thanh niên thế hệ Pavel tại khối các nước xã hội chủ nghĩa trong đó có Việt Nam','2022-01-21',777,2,'img11.jpg',145000),
(2,'Bố già','Mario Puzo','Thế giới ngầm được phản ánh trong tiểu thuyết  là Bố Già sự gặp gỡ giữa một bên là ý chí cương cường và nền tảng gia tộc chặt chẽ theo truyền thống mafia xứ Sicily với một bên là xã hội Mỹ nhập nhằng đen trắng, mảnh đất màu mỡ cho những cơ hội làm ăn bất chính hứa hẹn những món lợi kếch xù. Trong thế giới ấy, hình tượng Bố Già được tác giả dày công khắc họa đã trở thành bức chân dung bất hủ trong lòng người đọc. Từ một kẻ nhập cư tay trắng đến ông trùm tột đỉnh quyền uy, Don Vito Corleone là con rắn hổ mang thâm trầm, nguy hiểm khiến kẻ thù phải kiêng nể, e dè, nhưng cũng được bạn bè, thân quyến xem như một đấng toàn năng đầy nghĩa khí. Nhân vật trung tâm ấy đồng thời cũng là hiện thân của một pho triết lí rất “đời” được nhào nặn từ vốn sống của hàng chục năm lăn lộn giữa chốn giang hồ bao phen vào sinh ra tử, vì thế mà có ý kiến cho rằng “Bố Già là sự tổng hòa của mọi hiểu biết. Bố Già là đáp án cho mọi câu hỏi”.','2022-10-10',645,2,'img10.jpg',150000),
(3,'Những tù nhân của địa lý','Tim Marshall','“Khi chúng ta đang vươn tới những vì sao, chính bởi những thách thức đặt ra phía trước mà chúng ta có lẽ sẽ phải chung tay để ứng phó: du hành vào vũ trụ không phải với tư cách người Nga, người Trung Quốc hay người Mỹ, mà là những đại diện của nhân loại. Nhưng cho đến nay, mặc dù đã thoát khỏi sự kìm hãm của trọng lực, chúng ta vẫn đang bị giam giữ trong tâm trí của chính mình, bị giới hạn bởi sự nghi ngờ của mình về ‘kẻ khác’, và do đó bởi cuộc cạnh tranh chính yếu về tài nguyên. Phía trước chúng ta còn cả một chặng đường dài.”','2019-08-05',546,3,'img9.jpg',210000),
(4,'Ruồi trâu','Ethel E.Voynich','Nữ tiểu thuyết gia người Ireland Ethel Lilian Voynich (1864 – 1960) đã viết “Ruồi trâu” bằng tất cả ngọn lửa đam mê thổi hồn vào nhân vật trong bức tranh “Chân dung người không quen biết” của họa sĩ thế kỷ XVI Franciabigio mà bà được chiêm ngưỡng tại Bảo tàng Louvre (Pháp) vào năm mười bảy tuổi. Tác phẩm xuất bản lần đầu tại Anh và Hoa Kỳ năm 1897, và thực sự tạo tiếng vang rộng khắp sau khi được dịch sang tiếng Nga (1898). Vào thời điểm đó, “Ruồi trâu” là cuốn sách bán chạy nhất tại Nga và rất được ưa chuộng tại Trung Quốc. Ruồi trâu là một tác phẩm rất đáng đọc. Câu chuyện diễn ra vào thế kỉ 19 tại Ý. Thời đó, đất nước này đang bị chia cắt. Dưới quyền kiểm soát của đế quốc Áo, các phong trào cách mạng nhằm thống nhất nước Ý đều bị đàn áp. Một thanh niên Ý kiên cường đã dành cả cuộc đời mình để đấu tranh cho lí tưởng giải phóng đất nước.','2013-06-01',452,2,'img8.jpg',89000),
(5,'Búp sen xanh','Sơn Tùng','Búp Sen Xanh là nơi tiểu thuyết và lịch sử đã gặp nhau và hoạ nên một giai đoạn trong cuộc đời người Cha già của dân tộc Việt Nam. Nơi ấy, có quê nhà xứ Nghệ, có làng Sen, có khung dệt của mẹ, có lời dạy của cha, có những người bạn và những kỷ niệm ấu thơ. Nơi ấy có xứ Huế mà trong cuộc sống nghèo khổ có trăn trở tuổi trẻ, về con người, về vận mệnh dân tộc, có mất mát và đau thương...\r\n\r\nBúp Sen Xanh vượt ra ngoài những giới hạn của một tác phẩm thiếu nhi, có thể làm bất kỳ ai rung động đến rơi nước mắt trong đêm chia ly, khi người con từ biệt cha ra đi để tìm một con đường cho chính mình và cho dân tộc. Một phần cuộc đời, trọn vẹn lý tưởng và dấn thân... Búp Sen Xanh không chỉ là câu chuyện về lãnh tụ mà còn là câu chuyện để làm người.','2014-08-15',452,2,'img7.jpg',50000),
(6,'Chiến tranh và hòa bình','Lev N.Tolstoy','\"CHIẾN TRANH VÀ HÒA BÌNH\" – đại tiểu thuyết của đại văn hào Lev Tolstoy – sớm vượt ra khỏi biên giới lãnh thổ để được thế giới thừa nhận là thiên tiểu thuyết vĩ đại nhất mọi thời đại bởi những vấn đề lớn lao của cả nhân loại hiện lên sinh động và xúc động qua từng từ, từng câu bởi ngòi bút nghệ thuật trác việt của tác giả. “CHIẾN TRANH VÀ HÒA BÌNH” đã có ảnh hưởng lớn lao đối với sự phát triển của văn học Xô Viết và Tây Âu nói riêng, văn học thế giới nói chung. Bởi từ khi ra đời tới nay, bộ tiểu thuyết đã được xuất bản hàng nghìn lần bằng nhiều thứ tiếng khác nhau.','2013-12-16',723,2,'img6.jpg',340000),
(7,'Đắc nhân tâm','Dale Carnegie','Không còn nữa khái niệm giới hạn, Đắc Nhân Tâm là nghệ thuật thu phục lòng người, là làm cho tất cả mọi người yêu mến mình. Đắc nhân tâm và cái Tài trong mỗi người chúng ta. Đắc Nhân Tâm trong ý nghĩa đó cần được thụ đắc bằng sự hiểu rõ bản thân, thành thật với chính mình, hiểu biết và quan tâm đến những người xung quanh để nhìn ra và khơi gợi những tiềm năng ẩn khuất nơi họ, giúp họ phát triển lên một tầm cao mới. Đây chính là nghệ thuật cao nhất về con người và chính là ý nghĩa sâu sắc nhất đúc kết từ những nguyên tắc vàng của Dale Carnegie.','2015-09-12',345,7,'img4.jpg',76000),
(8,'Hai vạn dặm dưới biển','Jules Verne','Một con thủy quái khổng lồ bỗng nhiên xuất hiện làm điêu đứng cả ngành hàng hải.\r\n\r\nMột đoàn thám hiểm nhổ chiếc neo tàu ra khơi với nhiệm vụ tiêu diệt con quái vật ấy, dù có phải đánh đổi bằng cả mạng sống.\r\n\r\nMột chiếc tàu ngầm thoắt ẩn, thoắt hiện, cùng một vị thuyền trưởng mang trong mình lời thề sẽ không bao giờ can dự vào cuộc sống trên đất liền thêm một lần nào nữa…\r\n\r\nTất cả những bí mật sâu kín nhất của đại dương sâu thẳm, những phát minh chưa từng được biết đến, những mối nguy hiểm rình rập trong lòng biển cả… Tất cả đã quyện cùng với nhau để tạo nên một chuyến phiêu lưu li kì, hấp dẫn mà các bạn không thể bỏ qua, khi đã cầm trên tay cuốn sách “Hai vạn dặm dưới biển” của Jules Verne.','2019-03-12',254,6,'img3.jpg',102000),
(9,'Tây sở bá vương Hạng Võ','Thường vạn sinh','Một đời người gắn liền với tên tuổi một dòng sông. Con nước ầm ào sóng vỗ rồi lặng lẽ đến tàn nhẫn, cuốn phăng đi tất cả!\r\n\r\nDòng Ô Giang (một đoạn của sông Trường Giang) hùng tráng, sóng cả tung tóe một góc trời với khí thế hung hãn gợi lên hình ảnh kiêu hùng của Sở Bá Vương Hạng Võ, người đã viết nên những trang bi tráng một thời của lịch sử Trung Quốc.\r\n\r\nNhững chiến thắng vang lừng nối tiếp tưởng như đã mở ra cho con người võ nghệ cao cường, dũng cảm có thừa ấy một nghiệp bá rực rỡ, lâu bền. Nhưng rồi tất cả được khép lại bằng một chiến bại tức tưởi cũng cho chính nhân vật anh hùng nhưng thiếu mưu lược, không quyết đoán, lòng dạ hẹp hòi và hiếu sát ấy!\r\n\r\n\r\n\r\nChắc chỉ có dòng Ô Giang định mệnh kia mới lắng nghe tiếng than: “Trời đã bỏ ta!”, rồi cuốn cả âm thanh lẫn thân xác Hạng Võ trôi ra biển cả mà hôm nay trên bờ dòng sông lịch sử ấy, còn chăng là những trang viết hấp dẫn, cuốn hút người đọc lần theo những bước thăng trầm của người anh hùng kiệt xuất một thời! ','2019-08-09',352,2,'img2.jpg',80000),
(10,'Số đỏ','Vũ Trọng Phụng','Nhân vật chính của Số đỏ là Xuân - biệt danh là Xuân Tóc đỏ, từ chỗ là một kẻ bị coi là hạ lưu, bỗng nhảy lên tầng lớp danh giá của xã hội nhờ trào lưu Âu hóa của giới tiểu tư sản Hà Nội khi đó.\r\n\r\nSố đỏ được xây dựng theo thể chương hồi, mỗi chương vừa như một sketch (kịch ngắn) độc lập, lại vừa như một giai đoạn phiêu lưu. Những câu chuyện trong đó ẩn chứa lối sống đầy tha hóa của bộ phận tư sản Hà Nội thời bấy giờ. Ông chê bai những thói xấu ấy khiến cho nhiều giá trị cuộc sống bị đảo lộn và mất đi giá trị ban đầu của nó.\r\n\r\nSố đỏ ban đầu đã được viết ra để nhại những chương trình Âu hoá xã hội của Tự Lực văn đoàn, thành phần văn học độc chiếm văn đàn trên nhiều lãnh vực văn hóa xã hội, và cũng là đối thủ quyết liệt nhất của Vũ Trọng Phụng trên \"mặt trận tư tưởng\". Những mẫu hình họ Vũ đưa ra để chế giễu, hầu hết nằm trong chương trình Âu hoá, cải cách xã hội của Tự Lực văn đoàn với các khẩu hiệu: Âu hoá, theo mới, hoàn toàn theo mới không chút do dự, làm việc xã hội, theo chủ nghiã bình dân, vận động thể thao, luyện tập thân thể cường tráng, làm nhà ánh sáng, giải phóng phụ nữ, thiết kế y phục tân thời: kiểu áo Le mur Cát Tường v.v...','2015-07-20',157,2,'anh14.jpg',25600),
(11,'Kinh thánh tân ước','Không tõ',NULL,'2019-02-05',453,5,'img18.jpg',170000),
(12,'Tư bản','Karl Marx','\"Tư bản luận\" không chỉ là công trình nghiên cứu kinh tế chính trị mà còn chứa đựng những quan điểm triết học của Marx và những kiến thức về lịch sử của các nước Tây Âu.\r\n\r\nC. Mác là một trong những nhà tư tưởng vĩ đại nhất của nhân loại, một nhà bác học uyên bác trên nhiều lĩnh vực, lãnh tụ thiên tài của giai cấp công nhân và nhân dân lao động trên toàn thế giới. Để biên soạn bộ Tư bản, C. Mác đã dành 40 năm lao động không mệt mỏi, từ những năm 40 của thế kỷ XIX đến khi ông qua đời. ','2012-04-12',643,4,'img14.jpg',264000),
(13,'Không gia đình','Hector Malot','KHÔNG GIA ĐÌNH kể về cuộc đời của cậu bé Rémi. Từ nhỏ, Rémi đã bị bắt cóc, rồi bị bố nuôi bán cho một đoàn xiếc thú. Em đã theo đoàn xiếc ấy đi lưu lạc khắp nước Pháp.\r\n\r\nRémi đã lớn lên trong gian khổ và lao động để sống. Lúc đầu em được sự dạy bảo của cụ Vitalis, về sau thì tự lập. Không những lo cho mình, em còn lo việc biểu diễn và kiếm sống cho cả một gánh hát rong… Nhưng dù ở đâu, trong cảnh ngộ nào, em vẫn noi theo nếp rèn dạy của cụ Vitalis giữ phẩm chất làm người. Đó là lòng yêu lao động, tự trọng, ngay thẳng, biết nhớ ơn nghĩa và luôn luôn muốn làm người có ích.','2015-04-23',660,2,'img15.jpg',105000),
(14,'Bay vòng quanh mặt trăng','Jules Verne','Jules Verne đã dùng phòng thí nghiệm và kính viễn vọng để viết tiểu thuyết phiêu lưu. Ông không chỉ ghi lại quá trình các nhà bác học tạo nên những phát minh lớn, mà còn tiên đoán chúng. Đọc những tác phẩm cùa ông, ta có thể nhận thấy những mâu thuẫn vì trong số những ý tưởng khoa học còn có những kiến phát chưa thể xác minh. Và bỗng dưng năm sau, hoặc vài năm sau, những giả thuyết mà ông đặt ra mà đôi khi thậm chí ông còn không tin, đã trở thành sự thật, một cách hoàn toàn chính xác. Những nhà bác học đã hoàn toàn đồng tình với ý tưởng của ông, hay nói cách khác, ông đã đi trước họ!...','2009-04-23',310,6,'img16.png',100000),
(15,'Mãi mãi tuổi hai mươi','Nguyễn Văn Thạc','Đây là cuốn nhật ký đã làm rung động hàng triệu con tim của bạn đọc bởi vẻ đẹp tâm hồn thuần khiết, tình yêu quê hương đất nước nồng nàn thể hiện qua những cảm nhận bình dị của tác giả trong suốt những chặng đường hành quân.','2018-05-20',243,9,'img17.jpg',160000),
(16,'Hành trình vào tâm trái đất','Jules Verne','Hành trình vào tâm trái Đất là một cuốn tiểu thuyết khoa học viễn tưởng của Jules Verne xuất bản vào năm 1864. Câu chuyện kể về một giáo sư người Đức tin rằng có những miệng núi lửa có con đường đi thẳng xuống trung tâm Trái đất. Ông, đứa cháu trai Axel, và người dẫn đường Hans của họ đã trải qua những chuyến phiêu lưu thú vị, gặp gỡ những con vật thời tiền sử cùng các thảm họa tự nhiên, cuối cùng đã quay lại được mặt đất ở miền nam nước Ý. Các sinh vật sống mà họ gặp phải phù hợp với từng thời kỳ địa chất, cũng như các lớp đá dần càng cổ hơn khi họ đi xuống càng sâu, những loài vật cũng xưa hơn khi các nhân vật tiến gần đến tâm trái đất.','2018-11-14',386,6,'img19.jpg',85000),
(17,'Harry Porter','J.K.Rowling','Khi một lá thư được gởi đến cho cậu bé Harry Potter bình thường và bất hạnh, cậu khám phá ra một bí mật đã được che giấu suốt cả một thập kỉ. Cha mẹ cậu chính là phù thủy và cả hai đã bị lời nguyền của Chúa tể Hắc ám giết hại khi Harry mới chỉ là một đứa trẻ, và bằng cách nào đó, cậu đã giữ được mạng sống của mình. Thoát khỏi những người giám hộ Muggle không thể chịu đựng nổi để nhập học vào trường Hogwarts, một trường đào tạo phù thủy với những bóng ma và phép thuật, Harry tình cờ dấn thân vào một cuộc phiêu lưu đầy gai góc khi cậu phát hiện ra một con chó ba đầu đang canh giữ một căn phòng trên tầng ba. Rồi Harry nghe nói đến một viên đá bị mất tích sở hữu những sức mạnh lạ kì, rất quí giá, vô cùng nguy hiểm, mà cũng có thể là mang cả hai đặc điểm trên. Harry khổ sở mong ngóng cho kì nghỉ hè kinh khủng với gia đình Dursley kết thúc. Nhưng một con gia tinh bé nhỏ tội nghiệp đã cảnh báo cho Harry biết về mối nguy hiểm chết người đang chờ cậu ở trường Hogwarts. Trở lại trường học, Harry nghe một tin đồn đang lan truyền về phòng chứa bí mật, nơi cất giữ những bí ẩn đáng sợ dành cho giới phù thủy có nguồn gốc Muggle. Có kẻ nào đó đang phù phép làm tê liệt mọi người, khiến họ gần như đã chết, và một lời cảnh báo kinh hoàng được tìm thấy trên bức tường. Mối nghi ngờ hàng đầu – và luôn luôn sai lầm – là Harry. Nhưng một việc còn đen tối hơn thế đã được hé mở.','2020-07-15',2048,2,'anh13.jpg',355000),
(20,'Những người khốn khổ','Victor Hugo','Những người khốn khổ là một cuốn tiểu thuyết xã hội hiện đại, một thiên anh hùng ca bằng văn xuôi. Hugo diễn tả cuộc đời trăm ngàn khổ cực và tâm hồn vô cùng cao thượng của một người tù khổ sai, của một thiếu phụ bị xã hội tư bản chà đạp tàn bạo, của một nhà thơ trẻ anh dũng. Trong cuốn tiểu thuyết vĩ đại này, Hugo đứng hẳn về phía quần chúng khi mô tả về cuộc chiến hùng tráng của nhân dân cần lao Paris nổi dậy năm 1832 nhằm chống lại chính quyền phản động lúc bấy giờ.','2017-06-18',1376,2,'anh15.jpg',250000),
(22,'Cuộc chiến không hồi kết','Edword Klein','','2019-02-16',354,4,'anh16.jpg',150000),
(23,'Nhật ký Đặng Thùy Trâm','Đặng Thùy Trâm','Một cuốn nhật kí nhặt được bên xác của một nữ Việt Cộng đã suýt bị người lính Mỹ ném vào lửa, nhưng người phiên dịch đã khuyên anh ta nên giữ lại vì \"trong đó có lửa\". Nhật kí Đặng Thùy Trâm là những ghi chép hàng ngày của một người nữ bác sĩ về cuộc sống của chị nơi chiến tuyến. Cuốn nhật kí là thế giới riêng của người trí thức nhạy cảm mà không yếu đuối, tha thiết với cuộc sống mà không hề sợ hãi trước những gian nan. Ở đó ta vẫn gặp những băn khoăn trăn trở trước tình yêu, trước cuộc sống phức tạp hàng ngày, những nỗi buồn, nỗi nhớ nhung, sự cô đơn của một người con gái, nhưng đồng thời chúng ta cũng thấy được một ý chí mãnh liệt, những lời nói tự động viên cảnh tỉnh, một lòng can đảm phi thường - những điều đã làm nên một thế hệ anh hùng.','2022-06-01',296,9,'anh17.jpg',75500),
(24,'Quẳng gánh lo đi và vui sống','Dale Carnegie','Bất kỳ ai đang sống đều sẽ có những lo lắng thường trực về học hành, công việc, những hoá đơn, chuyện nhà cửa,… Cuộc sống không dễ dàng giải thoát bạn khỏi căng thẳng, ngược lại, nếu quá lo lắng, bạn có thể mắc bệnh trầm cảm. Quẳng Gánh Lo Đi Và Vui Sống khuyên bạn hãy khóa chặt dĩ vãng và tương lai lại để sống trong cái phòng kín mít của ngày hôm nay. Mọi vấn đề đều có thể được giải quyết, chỉ cần bạn bình tĩnh và xác định đúng hành động cần làm vào đúng thời điểm.','2022-12-14',312,7,'anh18.jpg',57620),
(25,'Cây cam ngọt của tôi','José Nauro Vasconselos ','Hãy làm quen với Zezé, cậu bé tinh nghịch siêu hạng đồng thời cũng đáng yêu bậc nhất, với ước mơ lớn lên trở thành nhà thơ cổ thắt nơ bướm. Chẳng phải ai cũng công nhận khoản “đáng yêu” kia đâu nhé. Bởi vì, ở cái xóm ngoại ô nghèo ấy, nỗi khắc khổ bủa vây đã che mờ mắt người ta trước trái tim thiện lương cùng trí tưởng tượng tuyệt vời của cậu bé con năm tuổi. Có hề gì đâu bao nhiêu là hắt hủi, đánh mắng, vì Zezé đã có một người bạn đặc biệt để trút nỗi lòng: cây cam ngọt nơi vườn sau. Và cả một người bạn nữa, bằng xương bằng thịt, một ngày kia xuất hiện, cho cậu bé nhạy cảm khôn sớm biết thế nào là trìu mến, thế nào là nỗi đau, và mãi mãi thay đổi cuộc đời cậu. Mở đầu bằng những thanh âm trong sáng và kết thúc lắng lại trong những nốt trầm hoài niệm, Cây cam ngọt của tôi khiến ta nhận ra vẻ đẹp thực sự của cuộc sống đến từ những điều giản dị như bông hoa trắng của cái cây sau nhà, và rằng cuộc đời thật khốn khổ nếu thiếu đi lòng yêu thương và niềm trắc ẩn. Cuốn sách kinh điển này bởi thế không ngừng khiến trái tim người đọc khắp thế giới thổn thức, kể từ khi ra mắt lần đầu năm 1968 tại Brazil.','2020-04-12',244,2,'anh19.jpg',70000),
(26,'AQ chính truyện','Lỗ Tấn','Câu chuyện kể lại cuộc phiêu lưu của A Q, một anh chàng thuộc tầng lớp bần nông ít học và không có nghề nghiệp ổn định. A Q nổi tiếng vì phương pháp thắng lợi tinh thần. Ví dụ như mỗi khi anh bị đánh thì anh lại cứ nghĩ \"chúng đang đánh bố của chúng\". AQ có nhiều tình huống lý luận đến \"điên khùng\". A Q hay bắt nạt kẻ kém may mắn hơn mình nhưng lại sợ hãi trước những kẻ hơn mình về địa vị, quyền lực hoặc sức mạnh. Anh ta tự thuyết phục bản thân rằng mình có tinh thần cao cả so với những kẻ áp bức mình ngay trong khi anh ta phải chịu đựng sự bạo ngược và áp bức của chúng. Lỗ Tấn đã cho thấy những sai lầm cực đoan của A Q, đó cũng là biểu hiện của tính cách dân tộc Trung Hoa thời bấy giờ. Kết thúc tác phẩm, hình ảnh A Q bị đưa ra pháp trường vì một tội nhỏ cũng thật là sâu sắc và châm biếm.','2018-05-04',356,2,'anh20.png',47500),
(27,'Chiến tranh tiền tệ','Tống Hồng Binh','Một khi đọc “Chiến tranh tiền tệ - Ai thật sự là người giàu nhất thế giới” bạn sẽ phải giật mình nhận ra một điều kinh khủng rằng, đằng sau những tờ giấy bạc chúng ta chi tiêu hàng ngày là cả một thế lực ngầm đáng sợ - một thế lực bí ẩn với quyền lực siêu nhiên có thể điều khiển cả thế giới rộng lớn này. “Chiến tranh tiền tệ - Ai thật sự là người giàu nhất thế giới” đề cập đến một cuộc chiến khốc liệt, không khoan nhượng và dai dẳng giữa một nhóm nhỏ các ông trùm tài chính – đứng đầu là gia tộc Rothschild – với các thể chế tài chính của nhiều quốc gia.\r\n\r\nĐồng thời, cuốn sách còn giúp bạn hiểu thêm nhiều điều, rằng Bill Gates chưa phải là người giàu nhất hành tinh, rằng tỉ lệ tử vong của các tổng thống Mỹ lại cao hơn tỉ lệ tử trận của binh lính Mỹ ngoài chiến trường, hay vì sao phố Wall lại mạo hiểm đổ hết vốn liếng của mình cho việc “đầu tư” vào Hitler. \r\n\r\nLà một cuốn sách làm sửng sốt những ai muốn tìm hiểu về bản chất của tiền tệ, để từ đó nhận ra những hiểm họa tài chính tiềm ẩn nhằm chuẩn bị tâm lý cho một cuộc chiến tiền tệ “không đổ máu”. Một cuốn sách bổ ích cho các chuyên gia quản lý tài chính, các nhà quản trị doanh nghiệp, các nhà đầu tư nhỏ, các giáo viên giảng dạy về tài chính – ngân hàng cũng như sinh viên các trường kinh tế. ','2022-02-14',1094,4,'anh21.jpg',542900),
(28,'Đường xưa mây trắng','Thích Nhất Hạnh','Đường Xưa Mây Trắng là một câu chuyện vô cùng lý thú về cuộc đời của Bụt được kể lại dưới ngòi bút hùng hồn đầy chất thơ của tác giả. Với văn phong nhẹ nhàng giản dị, với lối kể chuyện sinh động lôi cuốn, tác giả đã đưa chúng ta trở về tắm mình trong dòng sông Nguyên thỉ cách đây gần 2.600 năm, để được hiểu và gần gũi với một bậc giác ngộ mà cuộc đời của Ngài tỏa rạng nếp sống đầy tuệ giác và từ bi. Đọc Đường Xưa Mây Trắng cho chúng ta cảm tưởng như đang đọc một thiên tình sử, nhẹ nhàng, gần gũi mà sâu lắng.\r\n\r\nSách Đường Xưa Mây Trắng đã được dịch ra hơn 20 thứ tiếng, được tái bản nhiều lần ở các nước và được xếp vào hàng những quyển sách hay nhất của thế kỷ 20.','2022-05-23',713,5,'anh22.png',78100),
(29,'Tôi tài giỏi, bạn cũng thế','Adam Khoo','Khi bạn cầm trên tay quyển sách này, có nghĩa là bạn đã có chiếc chìa khóa đến sự thành công cùng bảng hướng dẫn sử dụng.\r\n\r\nTrong chúng ta, bất kỳ ai cũng muốn chính bản thân mình trở thành người tài giỏi, có thể giải quyết mọi vấn đề một cách hiệu quả nhất. Và để có được những điều đó quyển sách này sẽ giúp bạn bằng những hướng dẫn học tập chi tiết nhất.','2023-02-12',354,7,'anh23.png',108000),
(30,'Tuổi trẻ đáng giá bao nhiêu','Roise Nguyễn','\"Bạn hối tiếc vì không nắm bắt lấy một cơ hội nào đó, chẳng có ai phải mất ngủ.\r\nBạn trải qua những ngày tháng nhạt nhẽo với công việc bạn căm ghét, người ta chẳng hề bận lòng.\r\nBạn có chết mòn nơi xó tường với những ước mơ dang dở, đó không phải là việc của họ.\r\n\r\nSuy cho cùng, quyết định là ở bạn. Muốn có điều gì hay không là tùy bạn.\r\n\r\nNên hãy làm những điều bạn thích. Hãy đi theo tiếng nói trái tim. Hãy sống theo cách bạn cho là mình nên sống.\r\n\r\nVì sau tất cả, chẳng ai quan tâm.\"','2018-06-05',285,7,'anh24.png',80),
(31,'Nhà giả kim','Paulo Coelho','Tất cả những trải nghiệm trong chuyến phiêu du theo đuổi vận mệnh của mình đã giúp Santiago thấu hiểu được ý nghĩa sâu xa nhất của hạnh phúc, hòa hợp với vũ trụ và con người.\r\n\r\nTiểu thuyết Nhà giả kim của Paulo Coelho như một câu chuyện cổ tích giản dị, nhân ái, giàu chất thơ, thấm đẫm những minh triết huyền bí của phương Đông. Trong lần xuất bản đầu tiên tại Brazil vào năm 1988, sách chỉ bán được 900 bản. Nhưng, với số phận đặc biệt của cuốn sách dành cho toàn nhân loại, vượt ra ngoài biên giới quốc gia, Nhà giả kim đã làm rung động hàng triệu tâm hồn, trở thành một trong những cuốn sách bán chạy nhất mọi thời đại, và có thể làm thay đổi cuộc đời người đọc.','2020-04-14',228,2,'anh26.png',53000),
(32,'Code dạo ký sự ','Phạm Huy Hoàng','Lập trình viên đâu phải chỉ biết code','2019-03-15',300,8,'anh27.png',159000);

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `category` VALUES (1,'Lựa chọn thể loại'),
(2,'Tiểu thuyết'),
(3,'Khoa học địa chất'),
(4,'Chính trị'),
(5,'Tôn giáo'),
(6,'Khoa học viễn tưởng'),
(7,'Kĩ năng sống'),
(8,'Công nghệ thông tin'),
(9,'Hồi ký - Nhật ký');

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` text NOT NULL,
  `email` text NOT NULL,
  `address` text NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `customer` VALUES (1,'Nguyễn Hữu Tuấn','huutuan1705@gmail.com','Hoài Đức - Hà Nội','huutuan1705','12345'),
(2,'Phạm Thị Phương Anh','phuonganh1912@gmail.com','Ba Vì - Hà Nội','phuonganh123','12345'),
(3,'Đỗ Duy Kiên','ddki1912@gmail.com','Cầu Giấy - Hà Nội','kiendo123','12345'),
(4,'Đỗ Tuấn Khải','tuankhai@gmail.com','Phú Xuyên - Hà Nội','khaido456','12345'),
(5,'Phạm Hồng Phong','hongphong@gmail.com','Hà Đông - Hà Nội','hongphong123','12345'),
(6,'Đỗ Đức Thụ','monkey@gmail.com','Tiền Hải - Thái Bình','monkey123','12345'),
(7,'Mai Văn Đạt','vandat170802@gmail.com','Sơn Đồng - Hoài Đức','maidat','12345'),
(8,'Nguyễn Hữu Tuấn','huutuan052002@gmail.com','Hoài Đức - Hà Nội','huutuan123','12345');

CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `cid` int DEFAULT NULL,
  `totalmoney` double DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `order` VALUES (3,'2023-05-23',3,725000,0),(5,'2023-05-23',3,550000,1),
(6,'2023-05-26',1,439200,1),
(11,'2023-05-30',2,728000,0),
(12,'2023-05-30',1,1586000,1),
(13,'2023-05-30',6,1312000,0),
(15,'2023-05-30',2,127600,0),
(16,'2023-06-02',3,645000,2),
(17,'2023-06-07',4,260000,2),
(18,'2023-06-08',1,225000,0),
(19,'2023-06-08',7,300000,2),
(20,'2023-06-09',8,1973000,0);

CREATE TABLE `orderdetail` (
  `oid` int NOT NULL,
  `bid` int NOT NULL,
  `quantity` varchar(45) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`oid`,`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `orderdetail` VALUES (3,1,'5',145000),
(5,3,'1',210000),(5,6,'1',340000),(6,11,'1',439200),(11,2,'3',150000),
(11,5,'1',50000),(11,7,'3',76000),(12,1,'4',145000),(12,6,'2',340000),
(12,7,'1',76000),(12,20,'1',250000),(13,1,'3',145000),(13,3,'2',210000),
(13,8,'1',102000),(13,17,'1',355000),(15,8,'1',102000),(15,10,'1',25600),
(16,1,'1',145000),(16,20,'2',250000),(17,5,'2',50000),(17,30,'2',80000),
(18,1,'1',145000),(18,30,'1',80000),(19,2,'2',150000),(20,6,'2',340000),
(20,7,'3',76000),(20,17,'3',355000);

CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bid` int DEFAULT NULL,
  `cid` int DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `comment` text,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `review` VALUES (2,1,1,4,'Một tác phẩm thật sự rất tuyệt','2023-06-09 10:08:39');





