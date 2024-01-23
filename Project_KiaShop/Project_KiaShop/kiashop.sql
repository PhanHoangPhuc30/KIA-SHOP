USE [Project_KiAShop]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[uID] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](255) NULL,
	[email] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[phone] [int] NULL,
	[address] [nvarchar](255)NULL,
	[role] [int] NOT NULL,
	[pin] [int] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[uID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[cID] [int] IDENTITY(1,1) NOT NULL,
	[cName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[cID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Favorite]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Favorite](
	[AccountID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
 CONSTRAINT [PK_Favorite] PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC,
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Home]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Home](
	[int] [int] NULL,
	[name] [nvarchar](max) NULL,
	[image] [nvarchar](max) NULL,
	[price] [money] NULL,
	[title] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[cid] [int] NULL,
	[productID] [int] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[orderDate] [date] NOT NULL,
	[accountID] [int] NOT NULL,
	[addressReceive] [nvarchar](max) NULL,
	[sdt] [nvarchar](max) NULL,
	[name] [nvarchar](max) NULL,
	[totalPrice] [money] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[UnitPrice] [money] NOT NULL,
	[Quantity] [smallint] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC,
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[pID] [int] IDENTITY(1,1) NOT NULL,
	[pName] [nvarchar](max) NULL,
	[image] [nvarchar](max) NULL,
	[price] [money] NULL,
	[title] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[cID] [int] NULL,
	[pAmount] [int] NULL,
	[isDeleted] [int] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[pID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SubImage]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubImage](
	[subImageID] [int] IDENTITY(1,1) NOT NULL,
	[pID] [int] NULL,
	[SImage] [nvarchar](max) NULL,
 CONSTRAINT [PK_SubImage] PRIMARY KEY CLUSTERED 
(
	[subImageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[View]    Script Date: 10/31/2023 1:28:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[View](
	[viewed] [int] NULL
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([uID], [username], [email], [password], [phone], [address], [role], [pin] ) VALUES (1, N'Phan Phúc', N'Php3002@gmail.com', N'886d057a091559e2f5dff95d1d01360b', null, null, 1, N'12334')

SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([cID], [cName]) VALUES (1, N'Giày Nike')
INSERT [dbo].[Category] ([cID], [cName]) VALUES (8, N'Giày Adidas')
INSERT [dbo].[Category] ([cID], [cName]) VALUES (9, N'Giày Jordan')
INSERT [dbo].[Category] ([cID], [cName]) VALUES (10, N'Giày Vans')
INSERT [dbo].[Category] ([cID], [cName]) VALUES (11, N'Giày Converse')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (1, N'Vans Sk8-Hi True White Leather', N'https://i2.wp.com/statics.hnbmg.com/2016/02/vans-white-leather-sk8-hi-zip-spring-02.jpg?resize=560%2C420&ssl=1', 3500.0000, NULL, NULL, 10, 61)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (2, N'Jordan 4 Retro Off-White Sail (W)', N'https://static.nike.com/a/images/t_prod_ss/w_960,c_limit,f_auto/cc0bf425-a85f-4535-a0e8-d6cc5da84caa/womens-air-jordan-4-x-off-white-sail-release-date.jpg', 3420.0000, NULL, NULL, 9, 12)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (3, N'Adidas XPLR White Black', N'https://images.asos-media.com/products/adidas-originals-xplr-trainers-in-white-by8690/8266132-2?$XXL$&wid=513&fit=constrain', 2830.0000, NULL, NULL, 8, 67)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (4, N'Nike Air Force 1 Low Off-White', N'https://swagger.com.vn/wp-content/uploads/2020/03/nike-air-force-1-off-white.jpg', 1990.0000, NULL, NULL, 1, 64)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (5, N'Nike Air Max 97 Triple White Wolf Grey', N'https://cdn.vuahanghieu.com/unsafe/0x900/left/top/smart/filters:quality(90)/https://admin.vuahanghieu.com/upload/product/2020/07/giay-nike-air-max-97-white-921733-100-mau-trang-5f164221168df-21072020081721.jpg', 2500.0000, NULL, NULL, 1, 63)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (6, N'Nike Air Force 1 Low G-Dragon', N'https://static.nike.com/a/images/t_prod_ss/w_960,c_limit,f_auto/6b06ee79-4cef-45a0-a127-68f0d29d2bb7/air-force-1-low-x-peaceminusone-para-noise-release-date.jpg', 2200.0000, NULL, NULL, 1, 20)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (7, N'Nike Air Force 1 Low White ''07', N'https://sneakerholicvietnam.vn/wp-content/uploads/2020/08/giay-nike-chinh-hang-air-force-1-low-white-1.jpg', 2700.0000, NULL, NULL, 1, 62)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (8, N'Adidas Falcon Triple White (W)', N'https://product.hstatic.net/1000367250/product/b28128-4_5601b55e4fcb4cb590ccd37b38676786_master.jpg', 1750.0000, NULL, NULL, 8, 65)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (9, N'Jordan 1 Retro High Off-White White', N'https://fandy.vn/wp-content/uploads/2018/08/giay_jd_1s_offwhite_trang_01.jpg', 1630.0000, NULL, NULL, 9, 66)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (10, N'Adidas Prophere Triple White (W)', N'https://cdn.modesens.com/media/57633072', 1540.0000, NULL, NULL, 8, 68)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (11, N'Jordan 1 Mid Chicago Toe', N'https://cdn.webshopapp.com/shops/292955/files/328532320/air-jordan-1-mid-chicago-toe-gs.jpg', 1320.0000, NULL, NULL, 9, 69)
INSERT [dbo].[Home] ([int], [name], [image], [price], [title], [description], [cid], [productID]) VALUES (12, N'Jordan 1 Retro High Dior', N'https://i.pinimg.com/originals/16/cd/e3/16cde3c69f83fb223d939bf42b1b7fdd.png', 1100.0000, NULL, NULL, 9, 70)
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (1, CAST(N'2019-04-28' AS Date), 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (2, CAST(N'2020-08-30' AS Date), 9, NULL, NULL, NULL, NULL)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (3, CAST(N'2020-02-19' AS Date), 8, NULL, NULL, NULL, NULL)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (4, CAST(N'2019-06-02' AS Date), 10, NULL, NULL, NULL, NULL)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (5, CAST(N'2018-02-16' AS Date), 3, NULL, NULL, NULL, NULL)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (6, CAST(N'2020-04-30' AS Date), 3, NULL, NULL, NULL, NULL)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (7, CAST(N'2019-03-06' AS Date), 8, NULL, NULL, NULL, NULL)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (8, CAST(N'2016-03-28' AS Date), 10, NULL, NULL, NULL, NULL)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (12, CAST(N'2021-03-25' AS Date), 11, N'HN', N'123', N'123', 1800.0000)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (13, CAST(N'2021-03-25' AS Date), 11, N'123', N'123', N'123', 910.0000)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (14, CAST(N'2021-03-25' AS Date), 11, N'12345', N'12346807956', N'12346807956', 890.0000)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (15, CAST(N'2021-03-25' AS Date), 11, N'HN', N'123876hb', N'123876hb', 202.0000)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (16, CAST(N'2021-03-25' AS Date), 11, N'hcm', N'34823451', N'trinh thang binh', 3080.0000)
INSERT [dbo].[Order] ([id], [orderDate], [accountID], [addressReceive], [sdt], [name], [totalPrice]) VALUES (17, CAST(N'2023-10-30' AS Date), 11, N'Äan phÆ°á»£ng', N'0919721077', N'Ngoc Huy', 630.0000)
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (1, 4, 700.0000, 2)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (2, 18, 1750.0000, 3)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (3, 30, 3500.0000, 1)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (4, 15, 400.0000, 4)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (5, 6, 600.0000, 1)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (6, 4, 700.0000, 3)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (7, 4, 700.0000, 2)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (8, 4, 700.0000, 1)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (8, 15, 400.0000, 3)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (12, 6, 600.0000, 3)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (13, 3, 455.0000, 2)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (14, 1, 320.0000, 2)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (14, 5, 250.0000, 1)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (15, 51, 101.0000, 2)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (16, 68, 1540.0000, 2)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductID], [UnitPrice], [Quantity]) VALUES (17, 2, 630.0000, 1)
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (1, N'Jordan 1 Retro High Dark Mocha', N'https://stockx-360.imgix.net/Air-Jordan-1-Retro-High-Dark-Mocha/Images/Air-Jordan-1-Retro-High-Dark-Mocha/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1608736454&w=1000', 320.0000, NULL, NULL, 9, 0, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (2, N'Jordan 5 Retro Alternate Bel-Air', N'https://stockx-360.imgix.net/Air-Jordan-5-Retro-Alternate-Bel-Air/Images/Air-Jordan-5-Retro-Alternate-Bel-Air/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606316558&w=1000', 630.0000, NULL, NULL, 9, 19, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (3, N'Jordan 4 Retro SE 95 Neon', N'https://stockx-360.imgix.net/Air-Jordan-4-Retro-SE-Neon/Images/Air-Jordan-4-Retro-SE-Neon/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606315690&w=1000', 455.0000, NULL, NULL, 9, 13, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (4, N'Jordan 11 Retro Concord (2018)', N'https://stockx-360.imgix.net/Air-Jordan-11-Retro-Concord-2018/Images/Air-Jordan-11-Retro-Concord-2018/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606320605&w=1000', 700.0000, NULL, NULL, 9, 18, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (5, N'Jordan 3 Retro Laser Orange (W)', N'https://stockx-360.imgix.net/Air-Jordan-3-Retro-Laser-Orange-W/Images/Air-Jordan-3-Retro-Laser-Orange-W/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606316857&w=1000', 250.0000, NULL, NULL, 9, 18, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (6, N'Jordan 6 Retro Travis Scott', N'https://stockx-360.imgix.net/Air-Jordan-6-Retro-Travis-Scott/Images/Air-Jordan-6-Retro-Travis-Scott/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1611191963&w=1000', 600.0000, NULL, NULL, 9, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (7, N'Jordan 1 Retro High Off-White', N'https://stockx-360.imgix.net/Air-Jordan-1-Retro-High-Off-White-University-Blue/Images/Air-Jordan-1-Retro-High-Off-White-University-Blue/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606321054&w=1000', 750.0000, NULL, NULL, 9, 16, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (8, N'Jordan 9 Retro White Gym Red', N'https://stockx-360.imgix.net/Air-Jordan-9-Retro-White-Gym-Red/Images/Air-Jordan-9-Retro-White-Gym-Red/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606318371&w=1000', 230.0000, NULL, NULL, 9, 21, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (9, N'Jordan 2 Retro Silver Anniversary', N'https://stockx-360.imgix.net/Air-Jordan-2-Retro-Silver-Anniversary/Images/Air-Jordan-2-Retro-Silver-Anniversary/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607661133&w=1000', 740.0000, NULL, NULL, 9, 30, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (10, N'Jordan 7 Retro Miro', N'https://stockx-360.imgix.net/Air-Jordan-7-Retro-Miro/Images/Air-Jordan-7-Retro-Miro/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606316884&w=1000', 990.0000, NULL, NULL, 9, 7, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (11, N'Jordan 8 Retro Doernbecher', N'https://stockx-360.imgix.net/Air-Jordan-8-Retro-Doernbecher/Images/Air-Jordan-8-Retro-Doernbecher/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606319150&w=1000', 200.0000, NULL, NULL, 9, 8, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (12, N'Jordan 4 Retro Off-White Sail (W)', N'https://stockx-360.imgix.net/Air-Jordan-4-Retro-Off-White-Sail-W/Images/Air-Jordan-4-Retro-Off-White-Sail-W/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607655526&w=1000', 3420.0000, NULL, NULL, 9, 12, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (13, N'Nike LD Waffle Sacai White Nylon', N'https://stockx-360.imgix.net/Nike-LD-Waffle-Sacai-White-Nylon/Images/Nike-LD-Waffle-Sacai-White-Nylon/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606325338&w=1000', 450.0000, NULL, NULL, 1, 11, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (14, N'Nike Air Presto Off-White White (2018)', N'https://stockx-360.imgix.net/Nike-Air-Presto-Off-White-White-2018/Images/Nike-Air-Presto-Off-White-White-2018/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606325159&w=1000', 880.0000, NULL, NULL, 1, 38, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (15, N'Nike Air Fear of God 1 Oatmeal', N'https://stockx-360.imgix.net/Nike-Air-Fear-of-God-1-Oatmeal/Images/Nike-Air-Fear-of-God-1-Oatmeal/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606322408&w=1000', 400.0000, NULL, NULL, 1, 42, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (16, N'Nike Air Force 1 Low Off-White Volt', N'https://stockx-360.imgix.net/Nike-Air-Force-1-Low-Off-White-Volt/Images/Nike-Air-Force-1-Low-Off-White-Volt/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606320457&w=1000', 500.0000, NULL, NULL, 1, 8, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (17, N'Nike MAG Back to the Future (2011)', N'https://stockx-360.imgix.net/Nike-Air-Mag-Back-To-The-Future-BTTF/Images/Nike-Air-Mag-Back-To-The-Future-BTTF/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606324762&w=1000', 930.0000, NULL, NULL, 1, 5, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (18, N'Nike Air More Uptempo Supreme', N'https://stockx-360.imgix.net/Nike-Air-More-Uptempo-Supreme-Red/Images/Nike-Air-More-Uptempo-Supreme-Red/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606324529&w=1000', 750.0000, NULL, NULL, 1, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (19, N'Nike Air Yeezy 2 Red October', N'https://stockx-360.imgix.net/Nike-Air-Yeezy-2-Red-October/Images/Nike-Air-Yeezy-2-Red-October/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606315975&w=1000', 300.0000, NULL, NULL, 1, 14, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (20, N'Nike Air Force 1 Low G-Dragon', N'https://stockx-360.imgix.net/Nike-Air-Force-1-Low-G-Dragon-Peaceminusone-Para-Noise-20/Images/Nike-Air-Force-1-Low-G-Dragon-Peaceminusone-Para-Noise-20/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1609362259&w=1000', 2200.0000, NULL, NULL, 1, 17, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (21, N'Nike Air Max 1 Anniversary Red', N'https://stockx-360.imgix.net/Nike-Air-Max-1-Anniversary-Red-2017-Restock/Images/Nike-Air-Max-1-Anniversary-Red-2017-Restock/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606322418&w=1000', 540.0000, NULL, NULL, 1, 28, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (22, N'Nike SB Dunk Low Travis Scott (Regular Box)', N'https://stockx-360.imgix.net/Nike-SB-Dunk-Low-Travis-Scott/Images/Nike-SB-Dunk-Low-Travis-Scott/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606325738&w=1000', 500.0000, NULL, NULL, 1, 27, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (23, N'Nike Air Max 270 White Black (W)', N'https://stockx-360.imgix.net/Nike-Air-Max-270-White-Black-W/Images/Nike-Air-Max-270-White-Black-W/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1609438205&w=1000', 600.0000, NULL, NULL, 1, 34, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (24, N'Nike Cortez Basic Forrest Gump (2019)', N'https://stockx-360.imgix.net/Nike-Cortez-Basic-Forrest-Gump-2019/Images/Nike-Cortez-Basic-Forrest-Gump-2019/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607052040&w=1000', 300.0000, NULL, NULL, 1, 31, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (25, N'Adidas Yeezy Boost 350 V2 Black Red', N'https://stockx-360.imgix.net/adidas-Yeezy-Boost-350-V2-Core-Black-Red-2017/Images/adidas-Yeezy-Boost-350-V2-Core-Black-Red-2017/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606320792&w=1000', 200.0000, NULL, NULL, 8, 16, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (26, N'Adidas Yeezy 700 Mauve', N'https://stockx-360.imgix.net/adidas-Yeezy-700-Mauve/Images/adidas-Yeezy-700-Mauve/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606323073&w=1000', 450.0000, NULL, NULL, 8, 19, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (27, N'Adidas Yeezy 700 V3 Azael', N'https://stockx-360.imgix.net/adidas-Yeezy-700-V3-Azael/Images/adidas-Yeezy-700-V3-Azael/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606319725&w=1000', 300.0000, NULL, NULL, 8, 30, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (28, N'Adidas ZX 10000 C Meissen', N'https://stockx-360.imgix.net/adidas-ZX-10000-C-Meissen/Images/adidas-ZX-10000-C-Meissen/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607503920&w=1000', 500.0000, NULL, NULL, 8, 32, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (29, N'Adidas Superstar Sean Wotherspoon', N'https://stockx-360.imgix.net/adidas-Superstar-Sean-Wotherspoon-Superearth/Images/adidas-Superstar-Sean-Wotherspoon-Superearth/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607051820&w=1000', 200.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (30, N'Adidas NMD R1 Japan Triple White', N'https://stockx-360.imgix.net/adidas-NMD-R1-Japan-Triple-White/Images/adidas-NMD-R1-Japan-Triple-White/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606320555&w=1000', 540.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (31, N'Adidas Ultra Boost 2019 Panda', N'https://stockx-360.imgix.net/adidas-Ultra-Boost-2019-Panda/Images/adidas-Ultra-Boost-2019-Panda/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1611595580&w=1000', 150.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (32, N'Adidas Yeezy QNTM (Lifestyle Model)', N'https://stockx-360.imgix.net/adidas-Yzy-QNTM/Images/adidas-Yzy-QNTM/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606324011&w=1000', 800.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (33, N'Adidas Support Mid ADV Primeknit Dragon Ball', N'https://stockx-360.imgix.net/adidas-EQT-Support-Mid-ADV-Primeknit-Dragon-Ball-Z-Shenron/Images/adidas-EQT-Support-Mid-ADV-Primeknit-Dragon-Ball-Z-Shenron/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606937327&w=1000', 550.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (34, N'Adidas Stan Smith White Green (OG)', N'https://stockx-360.imgix.net/Adidas-Stan-Smith-White-Green-OG/Images/Adidas-Stan-Smith-White-Green-OG/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1603481985&w=1000', 340.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (35, N'Adidas Crazy 97 Kobe Bryant Slam Dunk', N'https://stockx-360.imgix.net/adidas-Crazy-97-EQT-Kobe-Bryant-1997-Slam-Dunk-Contest/Images/adidas-Crazy-97-EQT-Kobe-Bryant-1997-Slam-Dunk-Contest/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606935783&w=1000', 780.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (36, N'Adidas Pharrell NMD HU China Pack Peace', N'https://stockx-360.imgix.net/adidas-Pharrell-NMD-HU-China-Pack-Peace-Blue/Images/adidas-Pharrell-NMD-HU-China-Pack-Peace-Blue/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607647298&w=1000', 912.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (37, N'Vans Old Skool Yacht Club', N'https://stockx-360.imgix.net/Vans-Old-Skool-Yacht-Club/Images/Vans-Old-Skool-Yacht-Club/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607054491&w=1000', 347.0000, NULL, NULL, 10, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (38, N'Vans Old Skool Notre Blue', N'https://stockx-360.imgix.net/Vans-Old-Skool-Notre-Blue/Images/Vans-Old-Skool-Notre-Blue/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607013288&w=1000', 283.0000, NULL, NULL, 10, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (39, N'Vans Sk8-Hi MoMA Lyubov Popova', N'https://images.stockx.com/Vans-Sk8-Hi-MoMA-Lyubov-Popova.jpg?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1605304334&w=1000', 90.0000, NULL, NULL, 10, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (40, N'Vans Era U-Color Floral', N'https://images.stockx.com/Vans-Era-U-Color-Floral.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 50.0000, NULL, NULL, 10, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (41, N'Vans Old Skool Sandy Liang', N'https://images.stockx.com/Vans-Old-Skool-Sandy-Liang.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 130.0000, NULL, NULL, 10, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (42, N'Vans Old Skool True White (2019)', N'https://images.stockx.com/Vans-Old-Skool-True-White-2019.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 60.0000, NULL, NULL, 10, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (43, N'Vans Slip-On 47 V DX Fear of God Black White', N'https://images.stockx.com/Vans-Slip-On-47-V-DX-Fear-Of-God-Black-White.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 390.0000, NULL, NULL, 10, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (44, N'Vans Sk8-Hi Re-Issue Flags', N'https://images.stockx.com/images/Vans-Sk8-Hi-Re-Issue-Flags.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1609945849&w=1000', 60.0000, NULL, NULL, 10, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (45, N'Vans Authentic Comme des Garcons White', N'https://images.stockx.com/images/Vans-Authentic-Comme-des-Garcons-White-Japan.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1607646969&w=1000', 220.0000, NULL, NULL, 10, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (46, N'Vans Style 36 C2H4 50/50 Enlighten Trailblazer', N'https://images.stockx.com/images/Vans-Style-36-C2H4-50-50-Enlighten-Trailblazer.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1605740836&w=1000', 360.0000, NULL, NULL, 10, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (47, N'Vans ComfyCush Slip-Skool Checkerboard', N'https://stockx-360.imgix.net/Vans-ComfyCush-Slip-Skool-Checkerboard-Clear/Images/Vans-ComfyCush-Slip-Skool-Checkerboard-Clear/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1611166487&w=1000', 80.0000, NULL, NULL, 10, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (48, N'Vans Mountain Edition Fear of God Red', N'https://stockx-360.imgix.net/Vans-Mountain-Edition-35-X-Fear-Of-God-Red/Images/Vans-Mountain-Edition-35-X-Fear-Of-God-Red/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1603481985&w=1000', 299.0000, NULL, NULL, 10, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (49, N'Converse Hi Comme des Garcons PLAY White', N'https://stockx-360.imgix.net/Converse-Chuck-Taylor-All-Star-70s-Hi-Comme-des-Garcons-PLAY-White/Images/Converse-Chuck-Taylor-All-Star-70s-Hi-Comme-des-Garcons-PLAY-White/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607647598&w=1000', 198.0000, NULL, NULL, 11, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (50, N'Converse Hi Fear Of God Black Natural', N'https://stockx-360.imgix.net/Converse-Chuck-Taylor-All-Star-70s-Hi-Fear-Of-God-Black-Natural/Images/Converse-Chuck-Taylor-All-Star-70s-Hi-Fear-Of-God-Black-Natural/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607647045&w=1000', 216.0000, NULL, NULL, 11, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (51, N'Converse Hi Lay Zhang', N'https://stockx-360.imgix.net/Converse-Chuck-Taylor-All-Star-70s-Hi-Lay-Zhang/Images/Converse-Chuck-Taylor-All-Star-70s-Hi-Lay-Zhang/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1603481985&w=1000', 101.0000, NULL, NULL, 11, 8, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (52, N'Converse Hi Offspring Paisley Black', N'https://stockx-360.imgix.net/Converse-Chuck-Taylor-All-Star-70s-Hi-Offspring-Paisley-Black/Images/Converse-Chuck-Taylor-All-Star-70s-Hi-Offspring-Paisley-Black/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1609359219&w=1000', 170.0000, NULL, NULL, 11, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (53, N'Converse Run Star Hike Hi JW Anderson Black', N'https://stockx-360.imgix.net/Converse-Run-Star-Hike-Hi-JW-Anderson-Black/Images/Converse-Run-Star-Hike-Hi-JW-Anderson-Black/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607647104&w=1000', 385.0000, NULL, NULL, 11, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (54, N'Converse Hi Ambush White', N'https://stockx-360.imgix.net/Converse-Chuck-Taylor-All-Star-70s-Hi-Ambush-White/Images/Converse-Chuck-Taylor-All-Star-70s-Hi-Ambush-White/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1610080819&w=1000', 411.0000, NULL, NULL, 11, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (55, N'Converse All-Star Vulcanized Hi Off-White', N'https://stockx-360.imgix.net/Converse-Chuck-Taylor-All-Star-Hi-Off-White/Images/Converse-Chuck-Taylor-All-Star-Hi-Off-White/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1610079983&w=1000', 170.0000, NULL, NULL, 11, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (56, N'Converse All-Star 70s Hi Off-White', N'https://stockx-360.imgix.net/Converse-Chuck-Taylor-All-Star-70s-Hi-Off-White/Images/Converse-Chuck-Taylor-All-Star-70s-Hi-Off-White/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606318827&w=1000', 555.0000, NULL, NULL, 11, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (57, N'Converse Ox Archival Flame Print', N'https://images.stockx.com/Converse-Chuck-Taylor-All-Star-70s-Ox-Archival-Flame-Print.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 66.0000, NULL, NULL, 11, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (58, N'Converse Thunderbolt Ox Vince Staples', N'https://images.stockx.com/Converse-Thunderbolt-Ox-Vince-Staples.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 68.0000, NULL, NULL, 11, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (59, N'Converse Hi Miley Cyrus White Black Stars (W)', N'https://images.stockx.com/Converse-Chuck-Taylor-All-Star-Hi-Miley-Cyrus-White-Black-Stars-W.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 45.0000, NULL, NULL, 11, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (60, N'Converse G4 ABA', N'https://images.stockx.com/Converse-G4-ABA.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1605136563&w=1000', 250.0000, NULL, NULL, 11, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (61, N'Vans Sk8-Hi True White Leather', N'https://stockx-360.imgix.net/Vans-Sk8-Hi-True-White-Leather/Images/Vans-Sk8-Hi-True-White-Leather/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1603481985&w=1000', 3500.0000, NULL, NULL, 10, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (62, N'Nike Air Force 1 Low White ''07', N'https://stockx-360.imgix.net/Nike-Air-Force-1-Low-White-07/Images/Nike-Air-Force-1-Low-White-07/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1611163779&w=1000', 2700.0000, NULL, NULL, 1, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (63, N'Nike Air Max 97 Triple White Wolf Grey', N'https://stockx-360.imgix.net/Nike-Air-Max-97-White-Wolf-Grey/Images/Nike-Air-Max-97-White-Wolf-Grey/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607044794&w=1000', 2500.0000, NULL, NULL, 1, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (64, N'Nike Air Force 1 Low Off-White', N'https://stockx-360.imgix.net/Nike-Air-Force-1-Low-Off-White/Images/Nike-Air-Force-1-Low-Off-White/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1608516462&w=1000', 1990.0000, NULL, NULL, 1, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (65, N'Adidas Falcon Triple White (W)', N'https://stockx-360.imgix.net/adidas-Falcon-Triple-White-W/Images/adidas-Falcon-Triple-White-W/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1606939084&w=1000', 1750.0000, NULL, NULL, 8, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (66, N'Jordan 1 Retro High Off-White White', N'https://stockx-360.imgix.net/Air-Jordan-1-Retro-High-Off-White-White/Images/Air-Jordan-1-Retro-High-Off-White-White/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607656930&w=1000', 1630.0000, NULL, NULL, 9, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (67, N'Adidas XPLR White Black', N'https://images.stockx.com/adidas-X-PLR-White-Black.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 2830.0000, NULL, NULL, 8, 10, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (68, N'Adidas Prophere Triple White (W)', N'https://images.stockx.com/Adidas-Prophere-Triple-White-W.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000', 1540.0000, NULL, NULL, 8, 8, 0)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (69, N'Jordan 1 Mid Chicago Toe', N'https://stockx-360.imgix.net/Air-Jordan-1-Mid-Chicago-Toe/Images/Air-Jordan-1-Mid-Chicago-Toe/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1608736709&w=1000', 1320.0000, NULL, NULL, 9, 10, 1)
INSERT [dbo].[Product] ([pID], [pName], [image], [price], [title], [description], [cID], [pAmount], [isDeleted]) VALUES (70, N'Jordan 1 Retro High Dior', N'https://stockx-360.imgix.net/Air-Jordan-1-Retro-High-Dior/Images/Air-Jordan-1-Retro-High-Dior/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1607043976&w=1000', 1100.0000, NULL, NULL, 9, 10, 1)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[SubImage] ON 

INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (153, 39, N'https://images.stockx.com/Vans-Sk8-Hi-MoMA-Lyubov-Popova.jpg?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1605304334&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (154, 39, N'https://images.asos-media.com/products/vans-x-moma-lyubov-popova-sk8-hi-trainers-in-multi/20780496-4?$XXL$&wid=513&fit=constrain')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (155, 39, N'https://img01.ztat.net/article/spp-media-p1/9a17520ff22e36b8b9799a9cdbe30cab/d38f0ccee8d04643bb111a467b6f14f6.jpg?imwidth=762')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (156, 39, N'https://dxjz1362gavgl.cloudfront.net/resources/medias/shop/products/thumbnails/shop-brand-large/vans-x-moma-sk8-hi-vn0a4u3c22j-1.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (157, 40, N'https://images.stockx.com/Vans-Era-U-Color-Floral.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (158, 40, N'https://cdn.kickscrew.com/media/catalog/product/cache/74d578cb18fb245ef9256e5b9618a093/1/0/1025368_169421-2-2.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (159, 40, N'https://cdn.kickscrew.com/media/catalog/product/cache/74d578cb18fb245ef9256e5b9618a093/1/0/1025368_169421-1-1-1.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (160, 40, N'https://cdn.kickscrew.com/media/catalog/product/cache/74d578cb18fb245ef9256e5b9618a093/1/0/1025368_169421-1-2.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (161, 41, N'https://images.stockx.com/Vans-Old-Skool-Sandy-Liang.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (162, 41, N'https://static.sneakerjagers.com/products/660x660/140180.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (163, 41, N'https://www.vansshoesindia.in/images/large/vansshoesindia/Vans%20Women%20s%20x%20Sandy%20Liang%20Old%20Skool%2069_5_ZOOM.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (164, 41, N'https://image-cdn.hypb.st/http%3A%2F%2Fs3.store.hypebeast.com%2Fmedia%2Fimage%2F2a%2F54%2FShoes_2_4-d6d2b31f2808c34e1415cbf30423.jpg?fit=max&q=90&w=640&h=832&v=1')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (165, 42, N'https://images.stockx.com/Vans-Old-Skool-True-White-2019.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (166, 42, N'https://cdn-images.farfetch-contents.com/12/17/43/27/12174327_10284866_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (167, 42, N'https://cdn-images.farfetch-contents.com/12/17/43/27/12174327_10284863_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (168, 42, N'https://cdn-images.farfetch-contents.com/12/17/43/27/12174327_10284869_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (169, 43, N'https://images.stockx.com/Vans-Slip-On-47-V-DX-Fear-Of-God-Black-White.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (170, 43, N'https://cdn-images.farfetch-contents.com/12/96/75/99/12967599_13626296_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (171, 43, N'https://cdn-images.farfetch-contents.com/12/96/75/99/12967599_13626295_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (172, 43, N'https://cdn-images.farfetch-contents.com/12/96/75/99/12967599_13626297_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (173, 44, N'https://images.stockx.com/images/Vans-Sk8-Hi-Re-Issue-Flags.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1609945849&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (174, 44, N'http://static1.squarespace.com/static/532313ece4b08487acaec7a2/58659f59cd0f68096222ac74/5bd77b214785d34ca276ac55/1540848474688/rd_IMG_6578.jpg?format=1500w')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (175, 44, N'https://www.stadiumgoods.com/cdn-cgi/image/fit%3Dcontain%2Cformat%3Dauto%2Cwidth%3D720/media/catalog/product/V/N/VN0A2XSBRX0_2.png')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (176, 44, N'https://cdna.lystit.com/1200/630/tr/photos/stadiumgoods/f45a83f6/vans-FlagsMulti-Sk8-hi-Reissue-flags-Size-6.jpeg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (177, 45, N'https://images.stockx.com/images/Vans-Authentic-Comme-des-Garcons-White-Japan.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1607646969&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (178, 45, N'https://cdn-images.farfetch-contents.com/14/66/31/41/14663141_22899360_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (179, 45, N'https://cdn-images.farfetch-contents.com/14/66/31/41/14663141_22899366_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (180, 45, N'https://cdn-images.farfetch-contents.com/14/66/31/41/14663141_22899352_1000.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (181, 46, N'https://images.stockx.com/images/Vans-Style-36-C2H4-50-50-Enlighten-Trailblazer.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1605740836&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (182, 46, N'https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2020%2F11%2Fproject-50-50-c2h4-vans-enlighten-project-release-sk8-low-style-36-004.jpg?q=75&w=800&cbr=1&fit=max')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (183, 46, N'https://cdn.shortpixel.ai/spai/w_998+q_lossy+ret_img+to_webp/https://www.nicekicks.com/files/2020/11/C2H4-Vans-Trailblazer-00.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (184, 46, N'https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2020%2F11%2Fproject-50-50-c2h4-vans-enlighten-project-release-sk8-low-style-36-003.jpg?q=75&w=800&cbr=1&fit=max')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (219, 59, N'https://images.stockx.com/Converse-Chuck-Taylor-All-Star-Hi-Miley-Cyrus-White-Black-Stars-W.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (220, 59, N'https://www.efootwear.eu/media/catalog/product/cache/image/650x650/0/0/0000206622662_03__mt.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (230, 57, N'https://images.stockx.com/Converse-Chuck-Taylor-All-Star-70s-Ox-Archival-Flame-Print.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (231, 57, N'https://www.converse.com.vn/pictures/catalog/products/sneakers/chuck-taylor-all-star/167813/167813Cshot4.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (232, 57, N'https://www.converse.com.vn/pictures/catalog/products/sneakers/chuck-taylor-all-star/167813/167813Cshot1.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (233, 57, N'https://www.converse.com.vn/pictures/catalog/products/sneakers/chuck-taylor-all-star/167813/167813Cshot2.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (234, 58, N'https://images.stockx.com/Converse-Thunderbolt-Ox-Vince-Staples.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (235, 58, N'https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2018%2F12%2Fvince-staples-converse-thunderbolt-ox-white-black-grey-03.jpg?q=90&w=1400&cbr=1&fit=max')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (236, 58, N'https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2018%2F12%2Fvince-staples-converse-thunderbolt-ox-white-black-grey-2.jpg?q=90&w=1400&cbr=1&fit=max')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (237, 58, N'https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2018%2F12%2Fvince-staples-converse-thunderbolt-ox-white-black-grey-1.jpg?q=90&w=1400&cbr=1&fit=max')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (246, 59, N'https://www.efootwear.eu/media/catalog/product/cache/image/650x650/0/0/0000206622662_08__mt.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (247, 59, N'https://www.efootwear.eu/media/catalog/product/cache/image/650x650/0/0/0000206622662_01__mt.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (248, 60, N'https://images.stockx.com/Converse-G4-ABA.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1605136563&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (249, 60, N'https://www.converse.com/dw/image/v2/BCZC_PRD/on/demandware.static/-/Sites-cnv-master-catalog/default/dweee0ca97/images/h_08/169649C_H_08X1.jpg?sw=406')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (250, 60, N'https://www.converse.com/dw/image/v2/BCZC_PRD/on/demandware.static/-/Sites-cnv-master-catalog/default/dwe7bc708a/images/c_08/169649C_C_08X1.jpg?sw=406')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (251, 60, N'https://www.converse.com/dw/image/v2/BCZC_PRD/on/demandware.static/-/Sites-cnv-master-catalog/default/dw329d365d/images/d_08/169649C_D_08X1.jpg?sw=406')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (269, 67, N'https://images.stockx.com/adidas-X-PLR-White-Black.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (270, 67, N'http://www.beautevasion.fr/wp-content/uploads/2019/05/adidas-originals-xplr-blanc-homme-loisir-dc3a9tente_2.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (271, 67, N'http://www.beautevasion.fr/wp-content/uploads/2019/05/adidas-originals-xplr-blanc-homme-loisir-dc3a9tente_1.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (272, 67, N'http://www.beautevasion.fr/wp-content/uploads/2019/05/adidas-originals-xplr-blanc-homme-loisir-dc3a9tente.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (273, 68, N'https://images.stockx.com/Adidas-Prophere-Triple-White-W.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (274, 68, N'https://snkrvn.com/wp-content/uploads/2018/02/adidas-prophere-triple-white-release-info-6.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (275, 68, N'https://image.yes24.vn/Upload/ProductImage/ttquocte201603/1942088_1_L.jpg')
INSERT [dbo].[SubImage] ([subImageID], [pID], [SImage]) VALUES (276, 68, N'https://www.dandyfellow.com/images/prophere-p33819-207321_zoom.jpg')
SET IDENTITY_INSERT [dbo].[SubImage] OFF
GO
INSERT [dbo].[View] ([viewed]) VALUES (33)
GO
ALTER TABLE [dbo].[Favorite]  WITH CHECK ADD  CONSTRAINT [FK_Favorite_Account] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([uID])
GO
ALTER TABLE [dbo].[Favorite] CHECK CONSTRAINT [FK_Favorite_Account]
GO
ALTER TABLE [dbo].[Favorite]  WITH CHECK ADD  CONSTRAINT [FK_Favorite_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([pID])
GO
ALTER TABLE [dbo].[Favorite] CHECK CONSTRAINT [FK_Favorite_Product]
GO
ALTER TABLE [dbo].[Home]  WITH CHECK ADD  CONSTRAINT [FK_Home_Product] FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([pID])
GO
ALTER TABLE [dbo].[Home] CHECK CONSTRAINT [FK_Home_Product]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Account] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([uID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Account]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Order] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Order]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([pID])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([cID])
REFERENCES [dbo].[Category] ([cID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[SubImage]  WITH CHECK ADD  CONSTRAINT [FK_SubImage_Product] FOREIGN KEY([pID])
REFERENCES [dbo].[Product] ([pID])
GO
ALTER TABLE [dbo].[SubImage] CHECK CONSTRAINT [FK_SubImage_Product]
GO
