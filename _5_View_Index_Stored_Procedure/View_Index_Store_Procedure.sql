create database qlkh;

drop database qlkh;

create table qlkh.vattu(
mavtu int not null auto_increment,
tenvtu varchar(50),
dvtinh int,
phantram float,
primary key (mavtu)
);

create table qlkh.pxuat(
sopx int not null ,
ngayxuat datetime,
tenkh varchar(50),
primary key (sopx)
);

create table qlkh.ctpxuat(
sopx int not null,
mavtu int not null ,
slxuat int,
dgxuat float,
foreign key (sopx)references qlkh.pxuat(sopx),
foreign key (mavtu) references qlkh.vattu(mavtu)
);

create table qlkh.tonkho(
namthang datetime not null,
mavtu int not null,
foreign key (mavtu) references qlkh.vattu(mavtu),
sldau int,
tongsln int,
tongslx int,
slcuoi int,
primary key (namthang)
);

create table qlkh.pnhap(
sopn int not null,
ngaynhap datetime,
sodh int,
primary key (sopn)
);

alter table qlkh.pnhap
add foreign key (sodh) references qlkh.dondh(sodh);

create table qlkh.ctpnhap(
sopn int not null,
mavtu int not null,
slnhap int,
dgnhap float,
foreign key(sopn)references qlkh.pnhap(sopn),
foreign key (mavtu)references qlkh.vattu(mavtu)
);

create table qlkh.dondh(
sodh int not null,
ngaydh datetime,
manhacc int,
primary key (sodh)
);

alter table qlkh.dondh
add foreign key (manhacc) references qlkh.nhacc(manhacc);

create table qlkh.ctdondh(
sodh int not null ,
mavtu int not null ,
sldat int,
foreign key (sodh)references qlkh.dondh(sodh),
foreign key (mavtu) references qlkh.vattu(mavtu)
);

create table qlkh.nhacc(
manhacc int not null ,
tennhacc varchar(50),
diachi varchar(255),
dienthoai varchar(50),
primary key(manhacc)
);

insert into qlkh.vattu(mavtu, tenvtu, dvtinh, phantram) VALUES
(1,'vt1',1,70),
(2,'vt2',12,80),
(3,'vt3',1,60),
(4,'vt4',12,80),
(5,'vt5',12,90);

insert into qlkh.pxuat(sopx, ngayxuat, tenkh) VALUES
(1,'2020-04-06','a'),
(2,'2020-04-06','b'),
(3,'2020-04-06','c'),
(4,'2020-04-06','d'),
(5,'2020-04-06','e');

insert into qlkh.ctpxuat(sopx, mavtu, slxuat, dgxuat) VALUES
(1,1,1,10000),
(2,2,2,20000),
(3,3,3,15000),
(4,4,4,12000),
(5,5,5,10000);

insert into qlkh.nhacc(manhacc, tennhacc, diachi, dienthoai) VALUES
(1,'a1','abc','0123456789'),
(2,'a2','abcd','0123456789'),
(3,'a3','abce','0123456789'),
(4,'a4','abcf','0123456789'),
(5,'a5','abcg','0123456789');

insert into qlkh.dondh(sodh, ngaydh, manhacc) VALUES
(1,'2020-04-05',1),
(2,'2020-04-05',2),
(3,'2020-04-05',2),
(4,'2020-04-05',4),
(5,'2020-04-05',5);

insert into qlkh.pnhap(sopn, ngaynhap, sodh) VALUES
(1,'2020-03-06',1),
(2,'2020-03-06',2),
(3,'2020-03-06',2),
(4,'2020-03-06',4),
(5,'2020-03-06',5);

insert into qlkh.ctpnhap(sopn, mavtu, slnhap, dgnhap) VALUES
(1,1,1,10000),
(2,2,2,20000),
(3,3,3,15000),
(4,4,4,12000),
(5,5,5,10000);

insert into qlkh.ctdondh(sodh, mavtu, sldat) VALUES
(1,1,1),
(2,2,2),
(3,3,3),
(4,4,4),
(5,5,5);

insert into qlkh.tonkho(namthang, mavtu, sldau, tongsln, tongslx, slcuoi) VALUES
('2020-02-05',1,1,1,1,1),
('2020-01-05',2,2,2,2,2),
('2020-03-05',3,3,3,3,3),
('2020-05-05',4,4,4,4,4),
('2020-04-05',5,5,5,5,5);

/**1**/
create view qlkh.vw_ctpnhap as select sopn,ctpnhap.mavtu,slnhap,dgnhap,(slnhap*dgnhap) as thanhtiennhap
from qlkh.ctpnhap join qlkh.vattu
on ctpnhap.mavtu = vattu.mavtu;
/**2**/
create view qlkh.vw_ctpnhap_vt as select sopn,vattu.mavtu,vattu.tenvtu,slnhap,dgnhap,(slnhap*dgnhap) as thanhtiennhap
from qlkh.ctpnhap join qlkh.vattu
on ctpnhap.mavtu = vattu.mavtu;
/**3**/
create view qlkh.ctpnhap_vt_pn as select pnhap.sopn,pnhap.ngaynhap,pnhap.sodh,vattu.mavtu,vattu.tenvtu,ctpnhap.slnhap,ctpnhap.dgnhap,(slnhap*dgnhap) as thanhtiennhap
from qlkh.vattu join qlkh.ctpnhap
on vattu.mavtu = ctpnhap.mavtu join qlkh.pnhap
on ctpnhap.sopn = pnhap.sopn;
/**4**/
create view qlkh.ctpnhap_vt_pn_dh as select pnhap.sopn,pnhap.ngaynhap,manhacc,pnhap.sodh,vattu.mavtu,vattu.tenvtu,ctpnhap.slnhap,ctpnhap.dgnhap,(slnhap*dgnhap) as thanhtiennhap
from qlkh.vattu join qlkh.ctpnhap
on vattu.mavtu = ctpnhap.mavtu join qlkh.pnhap
on ctpnhap.sopn = pnhap.sopn join qlkh.dondh
on pnhap.sodh = dondh.sodh;
/**5**/
create view qlkh.vw_ctpnhap_loc as select sopn,mavtu,slnhap,dgnhap,(slnhap*dgnhap)as thanhtiennhap
from qlkh.ctpnhap where slnhap>5;
/**6**/
create view qlkh.vw_ctpnhap_vt_loc as select sopn,vattu.mavtu,vattu.tenvtu,slnhap,dgnhap,(slnhap*dgnhap) as thanhtiennhap
from qlkh.ctpnhap join qlkh.vattu
on ctpnhap.mavtu = vattu.mavtu
where dvtinh=12;
/**7**/
create view qlkh.vw_ctpxuat as select sopx,mavtu,slxuat,dgxuat,(slxuat*dgxuat)as thanhtienxuat
from qlkh.ctpxuat;
/**8**/
create view qlkh.vw_ctpxuat_vt as select sopx,vattu.mavtu,vattu.tenvtu,slxuat,dgxuat,(slxuat*dgxuat)as thanhtienxuat
from qlkh.ctpxuat join qlkh.vattu
on ctpxuat.mavtu = vattu.mavtu;
/**9**/
create view qlkh.vw_ctpxuat_vt_px as select pxuat.sopx,vattu.mavtu,vattu.tenvtu,tenkh,slxuat,dgxuat,(slxuat*dgxuat)as thanhtienxuat
from qlkh.ctpxuat join qlkh.vattu
on ctpxuat.mavtu = vattu.mavtu join qlkh.pxuat
on ctpxuat.sopx = pxuat.sopx;
/**stored procedure**/
/**1**/
create procedure qlkh.tong_so_luong_cuoi(in mavtu_nhap int)
begin
    select sum(slcuoi)
        from qlkh.tonkho
            where mavtu=mavtu_nhap;
end;
/**2**/
create procedure qlkh.tong_tien_xuat(in mavtu_nhap int)
begin
    select sum(slxuat*dgxuat)
        from qlkh.ctpxuat
            where mavtu=mavtu_nhap
    group by mavtu;
end;
/**3**/
create procedure qlkh.tong_so_luong_dat(in sodh_nhap int)
begin
    select sum(sldat)
    from qlkh.ctdondh
        where sodh_nhap=sodh;
end;
/**4**/
create procedure qlkh.them_don_dat_hang(sodh_nhap int,ngaydh_nhap datetime,manhacc_nhap int)
begin
    insert into qlkh.dondh value(sodh_nhap, ngaydh_nhap, manhacc_nhap);
end;
/**5**/
create procedure qlkh.them_chi_tiet_don_hang(sodh_nhap int,mavtu_nhap int,sldat_nhap int)
begin
    insert into qlkh.ctdondh values (sodh_nhap,mavtu_nhap,sldat_nhap);
end;