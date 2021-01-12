package com.zj.test.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zj.test.mp.enums.SexEnum;
import com.zj.test.mp.mapper.LogicDeleteTeacherMapper;
import com.zj.test.mp.mapper.PageMapper;
import com.zj.test.mp.mapper.TeacherMapper;
import com.zj.test.mp.po.LogicDeleteTeacher;
import com.zj.test.mp.po.Teacher;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/11 16:22
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = MpApplication.class)
@RunWith(SpringRunner.class)
public class MpUnitTest {

    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 1.测试: mybatis-plus集成测试
     *
     * 【测试输出】
     * 查询的结果:
     * [Teacher(id=2764815, name=name0, age=45), Teacher(id=2764816, name=name1, age=69), Teacher(id=2764817, name=name2, age=10), Teacher(id=2764818, name=name3, age=45), Teacher(id=2764819, name=name4, age=95), Teacher(id=2764820, name=name5, age=38), Teacher(id=2764821, name=name6, age=5), Teacher(id=2764822, name=name7, age=11), Teacher(id=2764823, name=name8, age=39), Teacher(id=2764824, name=name9, age=63), Teacher(id=2764825, name=name10, age=100), Teacher(id=2764826, name=name11, age=8), Teacher(id=2764827, name=name12, age=40), Teacher(id=2764828, name=name13, age=78), Teacher(id=2764829, name=name14, age=70), Teacher(id=2764830, name=name15, age=17), Teacher(id=2764831, name=name16, age=76), Teacher(id=2764832, name=name17, age=26), Teacher(id=2764833, name=name18, age=4), Teacher(id=2764834, name=name19, age=40), Teacher(id=2764835, name=name20, age=91), Teacher(id=2764836, name=name21, age=33), Teacher(id=2764837, name=name22, age=94), Teacher(id=2764838, name=name23, age=71), Teacher(id=2764839, name=name24, age=74), Teacher(id=2764840, name=name25, age=54), Teacher(id=2764841, name=name26, age=50), Teacher(id=2764842, name=name27, age=87), Teacher(id=2764843, name=name28, age=85), Teacher(id=2764844, name=name29, age=66), Teacher(id=2764845, name=name30, age=72), Teacher(id=2764846, name=name31, age=63), Teacher(id=2764847, name=name32, age=100), Teacher(id=2764848, name=name33, age=10), Teacher(id=2764849, name=name34, age=50), Teacher(id=2764850, name=name35, age=20), Teacher(id=2764851, name=name36, age=52), Teacher(id=2764852, name=name37, age=97), Teacher(id=2764853, name=name38, age=31), Teacher(id=2764854, name=name39, age=65), Teacher(id=2764855, name=name40, age=29), Teacher(id=2764856, name=name41, age=51), Teacher(id=2764857, name=name42, age=66), Teacher(id=2764858, name=name43, age=80), Teacher(id=2764859, name=name44, age=1), Teacher(id=2764860, name=name45, age=63), Teacher(id=2764861, name=name46, age=13), Teacher(id=2764862, name=name47, age=76), Teacher(id=2764863, name=name48, age=43), Teacher(id=2764864, name=name49, age=84), Teacher(id=2764865, name=name50, age=93), Teacher(id=2764866, name=name51, age=11), Teacher(id=2764867, name=name52, age=79), Teacher(id=2764868, name=name53, age=59), Teacher(id=2764869, name=name54, age=59), Teacher(id=2764870, name=name55, age=17), Teacher(id=2764871, name=name56, age=10), Teacher(id=2764872, name=name57, age=98), Teacher(id=2764873, name=name58, age=60), Teacher(id=2764874, name=name59, age=6), Teacher(id=2764875, name=name60, age=52), Teacher(id=2764876, name=name61, age=39), Teacher(id=2764877, name=name62, age=40), Teacher(id=2764878, name=name63, age=85), Teacher(id=2764879, name=name64, age=4), Teacher(id=2764880, name=name65, age=66), Teacher(id=2764881, name=name66, age=19), Teacher(id=2764882, name=name67, age=95), Teacher(id=2764883, name=name68, age=20), Teacher(id=2764884, name=name69, age=14), Teacher(id=2764885, name=name70, age=11), Teacher(id=2764886, name=name71, age=12), Teacher(id=2764887, name=name72, age=28), Teacher(id=2764888, name=name73, age=3), Teacher(id=2764889, name=name74, age=31), Teacher(id=2764890, name=name75, age=47), Teacher(id=2764891, name=name76, age=43), Teacher(id=2764892, name=name77, age=73), Teacher(id=2764893, name=name78, age=36), Teacher(id=2764894, name=name79, age=60), Teacher(id=2764895, name=name80, age=94), Teacher(id=2764896, name=name81, age=89), Teacher(id=2764897, name=name82, age=62), Teacher(id=2764898, name=name83, age=43), Teacher(id=2764899, name=name84, age=28), Teacher(id=2764900, name=name85, age=13), Teacher(id=2764901, name=name86, age=78), Teacher(id=2764902, name=name87, age=54), Teacher(id=2764903, name=name88, age=35), Teacher(id=2764904, name=name89, age=11), Teacher(id=2764905, name=name90, age=53), Teacher(id=2764906, name=name91, age=30), Teacher(id=2764907, name=name92, age=93), Teacher(id=2764908, name=name93, age=74), Teacher(id=2764909, name=name94, age=93), Teacher(id=2764910, name=name95, age=41), Teacher(id=2764911, name=name96, age=25), Teacher(id=2764912, name=name97, age=1), Teacher(id=2764913, name=name98, age=31), Teacher(id=2764914, name=name99, age=54), Teacher(id=2764915, name=name100, age=77), Teacher(id=2764916, name=name101, age=21), Teacher(id=2764917, name=name102, age=73), Teacher(id=2764918, name=name103, age=2), Teacher(id=2764919, name=name104, age=92), Teacher(id=2764920, name=name105, age=55), Teacher(id=2764921, name=name106, age=97), Teacher(id=2764922, name=name107, age=21), Teacher(id=2764923, name=name108, age=12), Teacher(id=2764924, name=name109, age=98), Teacher(id=2764925, name=name110, age=56), Teacher(id=2764926, name=name111, age=83), Teacher(id=2764927, name=name112, age=49), Teacher(id=2764928, name=name113, age=97), Teacher(id=2764929, name=name114, age=36), Teacher(id=2764930, name=name115, age=90), Teacher(id=2764931, name=name116, age=44), Teacher(id=2764932, name=name117, age=47), Teacher(id=2764933, name=name118, age=2), Teacher(id=2764934, name=name119, age=71), Teacher(id=2764935, name=name120, age=49), Teacher(id=2764936, name=name121, age=30), Teacher(id=2764937, name=name122, age=4), Teacher(id=2764938, name=name123, age=31), Teacher(id=2764939, name=name124, age=42), Teacher(id=2764940, name=name125, age=17), Teacher(id=2764941, name=name126, age=58), Teacher(id=2764942, name=name127, age=40), Teacher(id=2764943, name=name128, age=25), Teacher(id=2764944, name=name129, age=4), Teacher(id=2764945, name=name130, age=47), Teacher(id=2764946, name=name131, age=23), Teacher(id=2764947, name=name132, age=74), Teacher(id=2764948, name=name133, age=99), Teacher(id=2764949, name=name134, age=76), Teacher(id=2764950, name=name135, age=80), Teacher(id=2764951, name=name136, age=75), Teacher(id=2764952, name=name137, age=34), Teacher(id=2764953, name=name138, age=46), Teacher(id=2764954, name=name139, age=27), Teacher(id=2764955, name=name140, age=99), Teacher(id=2764956, name=name141, age=12), Teacher(id=2764957, name=name142, age=63), Teacher(id=2764958, name=name143, age=80), Teacher(id=2764959, name=name144, age=9), Teacher(id=2764960, name=name145, age=6), Teacher(id=2764961, name=name146, age=4), Teacher(id=2764962, name=name147, age=1), Teacher(id=2764963, name=name148, age=95), Teacher(id=2764964, name=name149, age=69), Teacher(id=2764965, name=name150, age=62), Teacher(id=2764966, name=name151, age=4), Teacher(id=2764967, name=name152, age=33), Teacher(id=2764968, name=name153, age=53), Teacher(id=2764969, name=name154, age=66), Teacher(id=2764970, name=name155, age=73), Teacher(id=2764971, name=name156, age=65), Teacher(id=2764972, name=name157, age=8), Teacher(id=2764973, name=name158, age=46), Teacher(id=2764974, name=name159, age=4), Teacher(id=2764975, name=name160, age=82), Teacher(id=2764976, name=name161, age=97), Teacher(id=2764977, name=name162, age=38), Teacher(id=2764978, name=name163, age=1), Teacher(id=2764979, name=name164, age=91), Teacher(id=2764980, name=name165, age=51), Teacher(id=2764981, name=name166, age=81), Teacher(id=2764982, name=name167, age=51), Teacher(id=2764983, name=name168, age=15), Teacher(id=2764984, name=name169, age=21), Teacher(id=2764985, name=name170, age=61), Teacher(id=2764986, name=name171, age=42), Teacher(id=2764987, name=name172, age=25), Teacher(id=2764988, name=name173, age=99), Teacher(id=2764989, name=name174, age=19), Teacher(id=2764990, name=name175, age=100), Teacher(id=2764991, name=name176, age=41), Teacher(id=2764992, name=name177, age=5), Teacher(id=2764993, name=name178, age=1), Teacher(id=2764994, name=name179, age=91), Teacher(id=2764995, name=name180, age=54), Teacher(id=2764996, name=name181, age=96), Teacher(id=2764997, name=name182, age=19), Teacher(id=2764998, name=name183, age=8), Teacher(id=2764999, name=name184, age=81), Teacher(id=2765000, name=name185, age=79), Teacher(id=2765001, name=name186, age=56), Teacher(id=2765002, name=name187, age=39), Teacher(id=2765003, name=name188, age=30), Teacher(id=2765004, name=name189, age=34), Teacher(id=2765005, name=name190, age=77), Teacher(id=2765006, name=name191, age=83), Teacher(id=2765007, name=name192, age=87), Teacher(id=2765008, name=name193, age=84), Teacher(id=2765009, name=name194, age=59), Teacher(id=2765010, name=name195, age=45), Teacher(id=2765011, name=name196, age=46), Teacher(id=2765012, name=name197, age=95), Teacher(id=2765013, name=name198, age=38), Teacher(id=2765014, name=name199, age=3), Teacher(id=2765015, name=name200, age=1), Teacher(id=2765016, name=name201, age=97), Teacher(id=2765017, name=name202, age=83), Teacher(id=2765018, name=name203, age=22), Teacher(id=2765019, name=name204, age=60), Teacher(id=2765020, name=name205, age=35), Teacher(id=2765021, name=name206, age=96), Teacher(id=2765022, name=name207, age=75), Teacher(id=2765023, name=name208, age=87), Teacher(id=2765024, name=name209, age=8), Teacher(id=2765025, name=name210, age=82), Teacher(id=2765026, name=name211, age=86), Teacher(id=2765027, name=name212, age=82), Teacher(id=2765028, name=name213, age=54), Teacher(id=2765029, name=name214, age=24), Teacher(id=2765030, name=name215, age=57), Teacher(id=2765031, name=name216, age=13), Teacher(id=2765032, name=name217, age=94), Teacher(id=2765033, name=name218, age=29), Teacher(id=2765034, name=name219, age=66), Teacher(id=2765035, name=name220, age=43), Teacher(id=2765036, name=name221, age=18), Teacher(id=2765037, name=name222, age=61), Teacher(id=2765038, name=name223, age=52), Teacher(id=2765039, name=name224, age=76), Teacher(id=2765040, name=name225, age=24), Teacher(id=2765041, name=name226, age=93), Teacher(id=2765042, name=name227, age=93), Teacher(id=2765043, name=name228, age=85), Teacher(id=2765044, name=name229, age=47), Teacher(id=2765045, name=name230, age=81), Teacher(id=2765046, name=name231, age=65), Teacher(id=2765047, name=name232, age=80), Teacher(id=2765048, name=name233, age=7), Teacher(id=2765049, name=name234, age=92), Teacher(id=2765050, name=name235, age=42), Teacher(id=2765051, name=name236, age=33), Teacher(id=2765052, name=name237, age=37), Teacher(id=2765053, name=name238, age=88), Teacher(id=2765054, name=name239, age=30), Teacher(id=2765055, name=name240, age=86), Teacher(id=2765056, name=name241, age=37), Teacher(id=2765057, name=name242, age=30), Teacher(id=2765058, name=name243, age=39), Teacher(id=2765059, name=name244, age=3), Teacher(id=2765060, name=name245, age=97), Teacher(id=2765061, name=name246, age=79), Teacher(id=2765062, name=name247, age=3), Teacher(id=2765063, name=name248, age=78), Teacher(id=2765064, name=name249, age=79), Teacher(id=2765065, name=name250, age=62), Teacher(id=2765066, name=name251, age=72), Teacher(id=2765067, name=name252, age=75), Teacher(id=2765068, name=name253, age=58), Teacher(id=2765069, name=name254, age=64), Teacher(id=2765070, name=name255, age=47), Teacher(id=2765071, name=name256, age=45), Teacher(id=2765072, name=name257, age=83), Teacher(id=2765073, name=name258, age=79), Teacher(id=2765074, name=name259, age=45), Teacher(id=2765075, name=name260, age=88), Teacher(id=2765076, name=name261, age=5), Teacher(id=2765077, name=name262, age=61), Teacher(id=2765078, name=name263, age=91), Teacher(id=2765079, name=name264, age=74), Teacher(id=2765080, name=name265, age=93), Teacher(id=2765081, name=name266, age=46), Teacher(id=2765082, name=name267, age=49), Teacher(id=2765083, name=name268, age=10), Teacher(id=2765084, name=name269, age=1), Teacher(id=2765085, name=name270, age=74), Teacher(id=2765086, name=name271, age=69), Teacher(id=2765087, name=name272, age=23), Teacher(id=2765088, name=name273, age=7), Teacher(id=2765089, name=name274, age=66), Teacher(id=2765090, name=name275, age=11), Teacher(id=2765091, name=name276, age=57), Teacher(id=2765092, name=name277, age=49), Teacher(id=2765093, name=name278, age=77), Teacher(id=2765094, name=name279, age=38), Teacher(id=2765095, name=name280, age=60), Teacher(id=2765096, name=name281, age=84), Teacher(id=2765097, name=name282, age=42), Teacher(id=2765098, name=name283, age=56), Teacher(id=2765099, name=name284, age=56), Teacher(id=2765100, name=name285, age=12), Teacher(id=2765101, name=name286, age=90), Teacher(id=2765102, name=name287, age=13), Teacher(id=2765103, name=name288, age=96), Teacher(id=2765104, name=name289, age=41), Teacher(id=2765105, name=name290, age=18), Teacher(id=2765106, name=name291, age=66), Teacher(id=2765107, name=name292, age=76), Teacher(id=2765108, name=name293, age=83), Teacher(id=2765109, name=name294, age=85), Teacher(id=2765110, name=name295, age=75), Teacher(id=2765111, name=name296, age=22), Teacher(id=2765112, name=name297, age=84), Teacher(id=2765113, name=name298, age=56), Teacher(id=2765114, name=name299, age=28), Teacher(id=2765115, name=name300, age=70), Teacher(id=2765116, name=name301, age=65), Teacher(id=2765117, name=name302, age=17), Teacher(id=2765118, name=name303, age=87), Teacher(id=2765119, name=name304, age=88), Teacher(id=2765120, name=name305, age=77), Teacher(id=2765121, name=name306, age=21), Teacher(id=2765122, name=name307, age=73), Teacher(id=2765123, name=name308, age=4), Teacher(id=2765124, name=name309, age=2), Teacher(id=2765125, name=name310, age=97), Teacher(id=2765126, name=name311, age=78), Teacher(id=2765127, name=name312, age=99), Teacher(id=2765128, name=name313, age=62), Teacher(id=2765129, name=name314, age=13), Teacher(id=2765130, name=name315, age=80), Teacher(id=2765131, name=name316, age=58), Teacher(id=2765132, name=name317, age=53), Teacher(id=2765133, name=name318, age=89), Teacher(id=2765134, name=name319, age=88), Teacher(id=2765135, name=name320, age=70), Teacher(id=2765136, name=name321, age=88), Teacher(id=2765137, name=name322, age=30), Teacher(id=2765138, name=name323, age=85), Teacher(id=2765139, name=name324, age=34), Teacher(id=2765140, name=name325, age=15), Teacher(id=2765141, name=name326, age=74), Teacher(id=2765142, name=name327, age=27), Teacher(id=2765143, name=name328, age=9), Teacher(id=2765144, name=name329, age=68), Teacher(id=2765145, name=name330, age=10), Teacher(id=2765146, name=name331, age=48), Teacher(id=2765147, name=name332, age=8), Teacher(id=2765148, name=name333, age=98), Teacher(id=2765149, name=name334, age=67), Teacher(id=2765150, name=name335, age=40), Teacher(id=2765151, name=name336, age=99), Teacher(id=2765152, name=name337, age=74), Teacher(id=2765153, name=name338, age=75), Teacher(id=2765154, name=name339, age=50), Teacher(id=2765155, name=name340, age=27), Teacher(id=2765156, name=name341, age=83), Teacher(id=2765157, name=name342, age=35), Teacher(id=2765158, name=name343, age=26), Teacher(id=2765159, name=name344, age=27), Teacher(id=2765160, name=name345, age=54), Teacher(id=2765161, name=name346, age=89), Teacher(id=2765162, name=name347, age=85), Teacher(id=2765163, name=name348, age=56), Teacher(id=2765164, name=name349, age=24), Teacher(id=2765165, name=name350, age=55), Teacher(id=2765166, name=name351, age=1), Teacher(id=2765167, name=name352, age=41), Teacher(id=2765168, name=name353, age=2), Teacher(id=2765169, name=name354, age=87), Teacher(id=2765170, name=name355, age=29), Teacher(id=2765171, name=name356, age=86), Teacher(id=2765172, name=name357, age=40), Teacher(id=2765173, name=name358, age=41), Teacher(id=2765174, name=name359, age=87), Teacher(id=2765175, name=name360, age=12), Teacher(id=2765176, name=name361, age=97), Teacher(id=2765177, name=name362, age=52), Teacher(id=2765178, name=name363, age=66), Teacher(id=2765179, name=name364, age=74), Teacher(id=2765180, name=name365, age=75), Teacher(id=2765181, name=name366, age=50), Teacher(id=2765182, name=name367, age=27), Teacher(id=2765183, name=name368, age=85), Teacher(id=2765184, name=name369, age=45), Teacher(id=2765185, name=name370, age=68), Teacher(id=2765186, name=name371, age=8), Teacher(id=2765187, name=name372, age=33), Teacher(id=2765188, name=name373, age=44), Teacher(id=2765189, name=name374, age=19), Teacher(id=2765190, name=name375, age=62), Teacher(id=2765191, name=name376, age=54), Teacher(id=2765192, name=name377, age=86), Teacher(id=2765193, name=name378, age=65), Teacher(id=2765194, name=name379, age=67), Teacher(id=2765195, name=name380, age=41), Teacher(id=2765196, name=name381, age=4), Teacher(id=2765197, name=name382, age=96), Teacher(id=2765198, name=name383, age=68), Teacher(id=2765199, name=name384, age=51), Teacher(id=2765200, name=name385, age=51), Teacher(id=2765201, name=name386, age=1), Teacher(id=2765202, name=name387, age=51), Teacher(id=2765203, name=name388, age=56), Teacher(id=2765204, name=name389, age=23), Teacher(id=2765205, name=name390, age=49), Teacher(id=2765206, name=name391, age=77), Teacher(id=2765207, name=name392, age=39), Teacher(id=2765208, name=name393, age=64), Teacher(id=2765209, name=name394, age=1), Teacher(id=2765210, name=name395, age=14), Teacher(id=2765211, name=name396, age=66), Teacher(id=2765212, name=name397, age=87), Teacher(id=2765213, name=name398, age=38), Teacher(id=2765214, name=name399, age=31), Teacher(id=2765215, name=name400, age=39), Teacher(id=2765216, name=name401, age=3), Teacher(id=2765217, name=name402, age=96), Teacher(id=2765218, name=name403, age=70), Teacher(id=2765219, name=name404, age=65), Teacher(id=2765220, name=name405, age=12), Teacher(id=2765221, name=name406, age=67), Teacher(id=2765222, name=name407, age=98), Teacher(id=2765223, name=name408, age=88), Teacher(id=2765224, name=name409, age=47), Teacher(id=2765225, name=name410, age=70), Teacher(id=2765226, name=name411, age=10), Teacher(id=2765227, name=name412, age=39), Teacher(id=2765228, name=name413, age=64), Teacher(id=2765229, name=name414, age=4), Teacher(id=2765230, name=name415, age=29), Teacher(id=2765231, name=name416, age=31), Teacher(id=2765232, name=name417, age=67), Teacher(id=2765233, name=name418, age=44), Teacher(id=2765234, name=name419, age=20), Teacher(id=2765235, name=name420, age=68), Teacher(id=2765236, name=name421, age=80), Teacher(id=2765237, name=name422, age=96), Teacher(id=2765238, name=name423, age=42), Teacher(id=2765239, name=name424, age=19), Teacher(id=2765240, name=name425, age=68), Teacher(id=2765241, name=name426, age=86), Teacher(id=2765242, name=name427, age=27), Teacher(id=2765243, name=name428, age=75), Teacher(id=2765244, name=name429, age=95), Teacher(id=2765245, name=name430, age=50), Teacher(id=2765246, name=name431, age=66), Teacher(id=2765247, name=name432, age=81), Teacher(id=2765248, name=name433, age=4), Teacher(id=2765249, name=name434, age=80), Teacher(id=2765250, name=name435, age=87), Teacher(id=2765251, name=name436, age=94), Teacher(id=2765252, name=name437, age=12), Teacher(id=2765253, name=name438, age=75), Teacher(id=2765254, name=name439, age=39), Teacher(id=2765255, name=name440, age=70), Teacher(id=2765256, name=name441, age=32), Teacher(id=2765257, name=name442, age=52), Teacher(id=2765258, name=name443, age=63), Teacher(id=2765259, name=name444, age=61), Teacher(id=2765260, name=name445, age=15), Teacher(id=2765261, name=name446, age=94), Teacher(id=2765262, name=name447, age=22), Teacher(id=2765263, name=name448, age=28), Teacher(id=2765264, name=name449, age=75), Teacher(id=2765265, name=name450, age=90), Teacher(id=2765266, name=name451, age=28), Teacher(id=2765267, name=name452, age=67), Teacher(id=2765268, name=name453, age=50), Teacher(id=2765269, name=name454, age=51), Teacher(id=2765270, name=name455, age=4), Teacher(id=2765271, name=name456, age=65), Teacher(id=2765272, name=name457, age=16), Teacher(id=2765273, name=name458, age=83), Teacher(id=2765274, name=name459, age=67), Teacher(id=2765275, name=name460, age=87), Teacher(id=2765276, name=name461, age=35), Teacher(id=2765277, name=name462, age=12), Teacher(id=2765278, name=name463, age=55), Teacher(id=2765279, name=name464, age=40), Teacher(id=2765280, name=name465, age=33), Teacher(id=2765281, name=name466, age=45), Teacher(id=2765282, name=name467, age=27), Teacher(id=2765283, name=name468, age=0), Teacher(id=2765284, name=name469, age=21), Teacher(id=2765285, name=name470, age=3), Teacher(id=2765286, name=name471, age=53), Teacher(id=2765287, name=name472, age=55), Teacher(id=2765288, name=name473, age=15), Teacher(id=2765289, name=name474, age=9), Teacher(id=2765290, name=name475, age=2), Teacher(id=2765291, name=name476, age=82), Teacher(id=2765292, name=name477, age=4), Teacher(id=2765293, name=name478, age=75), Teacher(id=2765294, name=name479, age=62), Teacher(id=2765295, name=name480, age=84), Teacher(id=2765296, name=name481, age=33), Teacher(id=2765297, name=name482, age=15), Teacher(id=2765298, name=name483, age=77), Teacher(id=2765299, name=name484, age=39), Teacher(id=2765300, name=name485, age=65), Teacher(id=2765301, name=name486, age=5), Teacher(id=2765302, name=name487, age=33), Teacher(id=2765303, name=name488, age=49), Teacher(id=2765304, name=name489, age=48), Teacher(id=2765305, name=name490, age=90), Teacher(id=2765306, name=name491, age=7), Teacher(id=2765307, name=name492, age=64), Teacher(id=2765308, name=name493, age=0), Teacher(id=2765309, name=name494, age=10), Teacher(id=2765310, name=name495, age=47), Teacher(id=2765311, name=name496, age=6), Teacher(id=2765312, name=name497, age=89), Teacher(id=2765313, name=name498, age=28), Teacher(id=2765314, name=name499, age=72), Teacher(id=2765315, name=name500, age=78), Teacher(id=2765316, name=name501, age=74), Teacher(id=2765317, name=name502, age=36), Teacher(id=2765318, name=name503, age=56), Teacher(id=2765319, name=name504, age=71), Teacher(id=2765320, name=name505, age=90), Teacher(id=2765321, name=name506, age=38), Teacher(id=2765322, name=name507, age=18), Teacher(id=2765323, name=name508, age=78), Teacher(id=2765324, name=name509, age=33), Teacher(id=2765325, name=name510, age=33), Teacher(id=2765326, name=name511, age=64), Teacher(id=2765327, name=name512, age=23), Teacher(id=2765328, name=name513, age=22), Teacher(id=2765329, name=name514, age=41), Teacher(id=2765330, name=name515, age=37), Teacher(id=2765331, name=name516, age=64), Teacher(id=2765332, name=name517, age=9), Teacher(id=2765333, name=name518, age=53), Teacher(id=2765334, name=name519, age=37), Teacher(id=2765335, name=name520, age=25), Teacher(id=2765336, name=name521, age=14), Teacher(id=2765337, name=name522, age=98), Teacher(id=2765338, name=name523, age=44), Teacher(id=2765339, name=name524, age=30), Teacher(id=2765340, name=name525, age=15), Teacher(id=2765341, name=name526, age=87), Teacher(id=2765342, name=name527, age=89), Teacher(id=2765343, name=name528, age=84), Teacher(id=2765344, name=name529, age=55), Teacher(id=2765345, name=name530, age=21), Teacher(id=2765346, name=name531, age=40), Teacher(id=2765347, name=name532, age=39), Teacher(id=2765348, name=name533, age=73), Teacher(id=2765349, name=name534, age=48), Teacher(id=2765350, name=name535, age=22), Teacher(id=2765351, name=name536, age=67), Teacher(id=2765352, name=name537, age=68), Teacher(id=2765353, name=name538, age=39), Teacher(id=2765354, name=name539, age=89), Teacher(id=2765355, name=name540, age=31), Teacher(id=2765356, name=name541, age=86), Teacher(id=2765357, name=name542, age=38), Teacher(id=2765358, name=name543, age=31), Teacher(id=2765359, name=name544, age=43), Teacher(id=2765360, name=name545, age=23), Teacher(id=2765361, name=name546, age=83), Teacher(id=2765362, name=name547, age=46), Teacher(id=2765363, name=name548, age=82), Teacher(id=2765364, name=name549, age=71), Teacher(id=2765365, name=name550, age=8), Teacher(id=2765366, name=name551, age=30), Teacher(id=2765367, name=name552, age=23), Teacher(id=2765368, name=name553, age=25), Teacher(id=2765369, name=name554, age=55), Teacher(id=2765370, name=name555, age=2), Teacher(id=2765371, name=name556, age=46), Teacher(id=2765372, name=name557, age=25), Teacher(id=2765373, name=name558, age=84), Teacher(id=2765374, name=name559, age=44), Teacher(id=2765375, name=name560, age=70), Teacher(id=2765376, name=name561, age=18), Teacher(id=2765377, name=name562, age=80), Teacher(id=2765378, name=name563, age=44), Teacher(id=2765379, name=name564, age=82), Teacher(id=2765380, name=name565, age=77), Teacher(id=2765381, name=name566, age=41), Teacher(id=2765382, name=name567, age=72), Teacher(id=2765383, name=name568, age=37), Teacher(id=2765384, name=name569, age=69), Teacher(id=2765385, name=name570, age=36), Teacher(id=2765386, name=name571, age=71), Teacher(id=2765387, name=name572, age=50), Teacher(id=2765388, name=name573, age=33), Teacher(id=2765389, name=name574, age=18), Teacher(id=2765390, name=name575, age=91), Teacher(id=2765391, name=name576, age=2), Teacher(id=2765392, name=name577, age=35), Teacher(id=2765393, name=name578, age=70), Teacher(id=2765394, name=name579, age=44), Teacher(id=2765395, name=name580, age=12), Teacher(id=2765396, name=name581, age=29), Teacher(id=2765397, name=name582, age=6), Teacher(id=2765398, name=name583, age=43), Teacher(id=2765399, name=name584, age=98), Teacher(id=2765400, name=name585, age=63), Teacher(id=2765401, name=name586, age=18), Teacher(id=2765402, name=name587, age=1), Teacher(id=2765403, name=name588, age=51), Teacher(id=2765404, name=name589, age=54), Teacher(id=2765405, name=name590, age=15), Teacher(id=2765406, name=name591, age=16), Teacher(id=2765407, name=name592, age=32), Teacher(id=2765408, name=name593, age=12), Teacher(id=2765409, name=name594, age=64), Teacher(id=2765410, name=name595, age=86), Teacher(id=2765411, name=name596, age=35), Teacher(id=2765412, name=name597, age=18), Teacher(id=2765413, name=name598, age=87), Teacher(id=2765414, name=name599, age=78), Teacher(id=2765415, name=name600, age=32), Teacher(id=2765416, name=name601, age=23), Teacher(id=2765417, name=name602, age=20), Teacher(id=2765418, name=name603, age=31), Teacher(id=2765419, name=name604, age=94), Teacher(id=2765420, name=name605, age=77), Teacher(id=2765421, name=name606, age=3), Teacher(id=2765422, name=name607, age=83), Teacher(id=2765423, name=name608, age=7), Teacher(id=2765424, name=name609, age=84), Teacher(id=2765425, name=name610, age=2), Teacher(id=2765426, name=name611, age=55), Teacher(id=2765427, name=name612, age=72), Teacher(id=2765428, name=name613, age=93), Teacher(id=2765429, name=name614, age=49), Teacher(id=2765430, name=name615, age=65), Teacher(id=2765431, name=name616, age=80), Teacher(id=2765432, name=name617, age=4), Teacher(id=2765433, name=name618, age=79), Teacher(id=2765434, name=name619, age=82), Teacher(id=2765435, name=name620, age=73), Teacher(id=2765436, name=name621, age=21), Teacher(id=2765437, name=name622, age=87), Teacher(id=2765438, name=name623, age=69), Teacher(id=2765439, name=name624, age=86), Teacher(id=2765440, name=name625, age=22), Teacher(id=2765441, name=name626, age=53), Teacher(id=2765442, name=name627, age=97), Teacher(id=2765443, name=name628, age=27), Teacher(id=2765444, name=name629, age=46), Teacher(id=2765445, name=name630, age=48), Teacher(id=2765446, name=name631, age=100), Teacher(id=2765447, name=name632, age=56), Teacher(id=2765448, name=name633, age=80), Teacher(id=2765449, name=name634, age=33), Teacher(id=2765450, name=name635, age=23), Teacher(id=2765451, name=name636, age=17), Teacher(id=2765452, name=name637, age=18), Teacher(id=2765453, name=name638, age=37), Teacher(id=2765454, name=name639, age=31), Teacher(id=2765455, name=name640, age=44), Teacher(id=2765456, name=name641, age=27), Teacher(id=2765457, name=name642, age=3), Teacher(id=2765458, name=name643, age=35), Teacher(id=2765459, name=name644, age=67), Teacher(id=2765460, name=name645, age=31), Teacher(id=2765461, name=name646, age=52), Teacher(id=2765462, name=name647, age=67), Teacher(id=2765463, name=name648, age=81), Teacher(id=2765464, name=name649, age=2), Teacher(id=2765465, name=name650, age=67), Teacher(id=2765466, name=name651, age=29), Teacher(id=2765467, name=name652, age=44), Teacher(id=2765468, name=name653, age=35), Teacher(id=2765469, name=name654, age=42), Teacher(id=2765470, name=name655, age=7), Teacher(id=2765471, name=name656, age=7), Teacher(id=2765472, name=name657, age=16), Teacher(id=2765473, name=name658, age=56), Teacher(id=2765474, name=name659, age=33), Teacher(id=2765475, name=name660, age=95), Teacher(id=2765476, name=name661, age=79), Teacher(id=2765477, name=name662, age=9), Teacher(id=2765478, name=name663, age=8), Teacher(id=2765479, name=name664, age=14), Teacher(id=2765480, name=name665, age=46), Teacher(id=2765481, name=name666, age=89), Teacher(id=2765482, name=name667, age=4), Teacher(id=2765483, name=name668, age=56), Teacher(id=2765484, name=name669, age=68), Teacher(id=2765485, name=name670, age=70), Teacher(id=2765486, name=name671, age=47), Teacher(id=2765487, name=name672, age=24), Teacher(id=2765488, name=name673, age=80), Teacher(id=2765489, name=name674, age=28), Teacher(id=2765490, name=name675, age=100), Teacher(id=2765491, name=name676, age=14), Teacher(id=2765492, name=name677, age=71), Teacher(id=2765493, name=name678, age=11), Teacher(id=2765494, name=name679, age=45), Teacher(id=2765495, name=name680, age=93), Teacher(id=2765496, name=name681, age=29), Teacher(id=2765497, name=name682, age=66), Teacher(id=2765498, name=name683, age=43), Teacher(id=2765499, name=name684, age=16), Teacher(id=2765500, name=name685, age=53), Teacher(id=2765501, name=name686, age=15), Teacher(id=2765502, name=name687, age=15), Teacher(id=2765503, name=name688, age=31), Teacher(id=2765504, name=name689, age=9), Teacher(id=2765505, name=name690, age=55), Teacher(id=2765506, name=name691, age=46), Teacher(id=2765507, name=name692, age=66), Teacher(id=2765508, name=name693, age=92), Teacher(id=2765509, name=name694, age=62), Teacher(id=2765510, name=name695, age=34), Teacher(id=2765511, name=name696, age=82), Teacher(id=2765512, name=name697, age=8), Teacher(id=2765513, name=name698, age=96), Teacher(id=2765514, name=name699, age=53), Teacher(id=2765515, name=name700, age=80), Teacher(id=2765516, name=name701, age=39), Teacher(id=2765517, name=name702, age=57), Teacher(id=2765518, name=name703, age=66), Teacher(id=2765519, name=name704, age=60), Teacher(id=2765520, name=name705, age=1), Teacher(id=2765521, name=name706, age=25), Teacher(id=2765522, name=name707, age=22), Teacher(id=2765523, name=name708, age=34), Teacher(id=2765524, name=name709, age=7), Teacher(id=2765525, name=name710, age=31), Teacher(id=2765526, name=name711, age=34), Teacher(id=2765527, name=name712, age=78), Teacher(id=2765528, name=name713, age=88), Teacher(id=2765529, name=name714, age=5), Teacher(id=2765530, name=name715, age=63), Teacher(id=2765531, name=name716, age=98), Teacher(id=2765532, name=name717, age=3), Teacher(id=2765533, name=name718, age=22), Teacher(id=2765534, name=name719, age=100), Teacher(id=2765535, name=name720, age=33), Teacher(id=2765536, name=name721, age=64), Teacher(id=2765537, name=name722, age=22), Teacher(id=2765538, name=name723, age=16), Teacher(id=2765539, name=name724, age=17), Teacher(id=2765540, name=name725, age=37), Teacher(id=2765541, name=name726, age=33), Teacher(id=2765542, name=name727, age=55), Teacher(id=2765543, name=name728, age=77), Teacher(id=2765544, name=name729, age=20), Teacher(id=2765545, name=name730, age=66), Teacher(id=2765546, name=name731, age=73), Teacher(id=2765547, name=name732, age=67), Teacher(id=2765548, name=name733, age=16), Teacher(id=2765549, name=name734, age=77), Teacher(id=2765550, name=name735, age=37), Teacher(id=2765551, name=name736, age=56), Teacher(id=2765552, name=name737, age=70), Teacher(id=2765553, name=name738, age=81), Teacher(id=2765554, name=name739, age=96), Teacher(id=2765555, name=name740, age=37), Teacher(id=2765556, name=name741, age=98), Teacher(id=2765557, name=name742, age=78), Teacher(id=2765558, name=name743, age=95), Teacher(id=2765559, name=name744, age=40), Teacher(id=2765560, name=name745, age=15), Teacher(id=2765561, name=name746, age=54), Teacher(id=2765562, name=name747, age=28), Teacher(id=2765563, name=name748, age=77), Teacher(id=2765564, name=name749, age=2), Teacher(id=2765565, name=name750, age=77), Teacher(id=2765566, name=name751, age=81), Teacher(id=2765567, name=name752, age=75), Teacher(id=2765568, name=name753, age=30), Teacher(id=2765569, name=name754, age=27), Teacher(id=2765570, name=name755, age=44), Teacher(id=2765571, name=name756, age=39), Teacher(id=2765572, name=name757, age=63), Teacher(id=2765573, name=name758, age=0), Teacher(id=2765574, name=name759, age=10), Teacher(id=2765575, name=name760, age=50), Teacher(id=2765576, name=name761, age=19), Teacher(id=2765577, name=name762, age=45), Teacher(id=2765578, name=name763, age=68), Teacher(id=2765579, name=name764, age=5), Teacher(id=2765580, name=name765, age=20), Teacher(id=2765581, name=name766, age=85), Teacher(id=2765582, name=name767, age=66), Teacher(id=2765583, name=name768, age=74), Teacher(id=2765584, name=name769, age=72), Teacher(id=2765585, name=name770, age=40), Teacher(id=2765586, name=name771, age=83), Teacher(id=2765587, name=name772, age=94), Teacher(id=2765588, name=name773, age=21), Teacher(id=2765589, name=name774, age=22), Teacher(id=2765590, name=name775, age=49), Teacher(id=2765591, name=name776, age=76), Teacher(id=2765592, name=name777, age=36), Teacher(id=2765593, name=name778, age=52), Teacher(id=2765594, name=name779, age=52), Teacher(id=2765595, name=name780, age=5), Teacher(id=2765596, name=name781, age=66), Teacher(id=2765597, name=name782, age=18), Teacher(id=2765598, name=name783, age=90), Teacher(id=2765599, name=name784, age=97), Teacher(id=2765600, name=name785, age=16), Teacher(id=2765601, name=name786, age=87), Teacher(id=2765602, name=name787, age=86), Teacher(id=2765603, name=name788, age=72), Teacher(id=2765604, name=name789, age=100), Teacher(id=2765605, name=name790, age=83), Teacher(id=2765606, name=name791, age=14), Teacher(id=2765607, name=name792, age=22), Teacher(id=2765608, name=name793, age=70), Teacher(id=2765609, name=name794, age=83), Teacher(id=2765610, name=name795, age=5), Teacher(id=2765611, name=name796, age=78), Teacher(id=2765612, name=name797, age=74), Teacher(id=2765613, name=name798, age=35), Teacher(id=2765614, name=name799, age=53), Teacher(id=2765615, name=name800, age=60), Teacher(id=2765616, name=name801, age=40), Teacher(id=2765617, name=name802, age=19), Teacher(id=2765618, name=name803, age=78), Teacher(id=2765619, name=name804, age=33), Teacher(id=2765620, name=name805, age=30), Teacher(id=2765621, name=name806, age=52), Teacher(id=2765622, name=name807, age=71), Teacher(id=2765623, name=name808, age=96), Teacher(id=2765624, name=name809, age=69), Teacher(id=2765625, name=name810, age=55), Teacher(id=2765626, name=name811, age=68), Teacher(id=2765627, name=name812, age=77), Teacher(id=2765628, name=name813, age=81), Teacher(id=2765629, name=name814, age=73), Teacher(id=2765630, name=name815, age=24), Teacher(id=2765631, name=name816, age=99), Teacher(id=2765632, name=name817, age=25), Teacher(id=2765633, name=name818, age=25), Teacher(id=2765634, name=name819, age=53), Teacher(id=2765635, name=name820, age=89), Teacher(id=2765636, name=name821, age=87), Teacher(id=2765637, name=name822, age=68), Teacher(id=2765638, name=name823, age=78), Teacher(id=2765639, name=name824, age=86), Teacher(id=2765640, name=name825, age=94), Teacher(id=2765641, name=name826, age=14), Teacher(id=2765642, name=name827, age=90), Teacher(id=2765643, name=name828, age=6), Teacher(id=2765644, name=name829, age=59), Teacher(id=2765645, name=name830, age=76), Teacher(id=2765646, name=name831, age=4), Teacher(id=2765647, name=name832, age=90), Teacher(id=2765648, name=name833, age=40), Teacher(id=2765649, name=name834, age=30), Teacher(id=2765650, name=name835, age=30), Teacher(id=2765651, name=name836, age=58), Teacher(id=2765652, name=name837, age=3), Teacher(id=2765653, name=name838, age=42), Teacher(id=2765654, name=name839, age=97), Teacher(id=2765655, name=name840, age=62), Teacher(id=2765656, name=name841, age=19), Teacher(id=2765657, name=name842, age=9), Teacher(id=2765658, name=name843, age=86), Teacher(id=2765659, name=name844, age=2), Teacher(id=2765660, name=name845, age=54), Teacher(id=2765661, name=name846, age=64), Teacher(id=2765662, name=name847, age=59), Teacher(id=2765663, name=name848, age=2), Teacher(id=2765664, name=name849, age=31), Teacher(id=2765665, name=name850, age=51), Teacher(id=2765666, name=name851, age=63), Teacher(id=2765667, name=name852, age=60), Teacher(id=2765668, name=name853, age=12), Teacher(id=2765669, name=name854, age=78), Teacher(id=2765670, name=name855, age=55), Teacher(id=2765671, name=name856, age=40), Teacher(id=2765672, name=name857, age=35), Teacher(id=2765673, name=name858, age=55), Teacher(id=2765674, name=name859, age=72), Teacher(id=2765675, name=name860, age=95), Teacher(id=2765676, name=name861, age=59), Teacher(id=2765677, name=name862, age=8), Teacher(id=2765678, name=name863, age=64), Teacher(id=2765679, name=name864, age=94), Teacher(id=2765680, name=name865, age=80), Teacher(id=2765681, name=name866, age=19), Teacher(id=2765682, name=name867, age=53), Teacher(id=2765683, name=name868, age=8), Teacher(id=2765684, name=name869, age=83), Teacher(id=2765685, name=name870, age=90), Teacher(id=2765686, name=name871, age=100), Teacher(id=2765687, name=name872, age=29), Teacher(id=2765688, name=name873, age=46), Teacher(id=2765689, name=name874, age=45), Teacher(id=2765690, name=name875, age=86), Teacher(id=2765691, name=name876, age=95), Teacher(id=2765692, name=name877, age=15), Teacher(id=2765693, name=name878, age=93), Teacher(id=2765694, name=name879, age=17), Teacher(id=2765695, name=name880, age=6), Teacher(id=2765696, name=name881, age=80), Teacher(id=2765697, name=name882, age=83), Teacher(id=2765698, name=name883, age=72), Teacher(id=2765699, name=name884, age=14), Teacher(id=2765700, name=name885, age=53), Teacher(id=2765701, name=name886, age=21), Teacher(id=2765702, name=name887, age=49), Teacher(id=2765703, name=name888, age=80), Teacher(id=2765704, name=name889, age=54), Teacher(id=2765705, name=name890, age=31), Teacher(id=2765706, name=name891, age=92), Teacher(id=2765707, name=name892, age=69), Teacher(id=2765708, name=name893, age=67), Teacher(id=2765709, name=name894, age=28), Teacher(id=2765710, name=name895, age=40), Teacher(id=2765711, name=name896, age=18), Teacher(id=2765712, name=name897, age=67), Teacher(id=2765713, name=name898, age=81), Teacher(id=2765714, name=name899, age=5), Teacher(id=2765715, name=name900, age=80), Teacher(id=2765716, name=name901, age=88), Teacher(id=2765717, name=name902, age=98), Teacher(id=2765718, name=name903, age=26), Teacher(id=2765719, name=name904, age=38), Teacher(id=2765720, name=name905, age=12), Teacher(id=2765721, name=name906, age=47), Teacher(id=2765722, name=name907, age=97), Teacher(id=2765723, name=name908, age=44), Teacher(id=2765724, name=name909, age=28), Teacher(id=2765725, name=name910, age=10), Teacher(id=2765726, name=name911, age=66), Teacher(id=2765727, name=name912, age=98), Teacher(id=2765728, name=name913, age=92), Teacher(id=2765729, name=name914, age=65), Teacher(id=2765730, name=name915, age=51), Teacher(id=2765731, name=name916, age=61), Teacher(id=2765732, name=name917, age=53), Teacher(id=2765733, name=name918, age=79), Teacher(id=2765734, name=name919, age=39), Teacher(id=2765735, name=name920, age=57), Teacher(id=2765736, name=name921, age=66), Teacher(id=2765737, name=name922, age=61), Teacher(id=2765738, name=name923, age=6), Teacher(id=2765739, name=name924, age=45), Teacher(id=2765740, name=name925, age=10), Teacher(id=2765741, name=name926, age=14), Teacher(id=2765742, name=name927, age=40), Teacher(id=2765743, name=name928, age=58), Teacher(id=2765744, name=name929, age=71), Teacher(id=2765745, name=name930, age=81), Teacher(id=2765746, name=name931, age=91), Teacher(id=2765747, name=name932, age=14), Teacher(id=2765748, name=name933, age=96), Teacher(id=2765749, name=name934, age=36), Teacher(id=2765750, name=name935, age=93), Teacher(id=2765751, name=name936, age=56), Teacher(id=2765752, name=name937, age=1), Teacher(id=2765753, name=name938, age=39), Teacher(id=2765754, name=name939, age=90), Teacher(id=2765755, name=name940, age=32), Teacher(id=2765756, name=name941, age=93), Teacher(id=2765757, name=name942, age=69), Teacher(id=2765758, name=name943, age=64), Teacher(id=2765759, name=name944, age=16), Teacher(id=2765760, name=name945, age=86), Teacher(id=2765761, name=name946, age=81), Teacher(id=2765762, name=name947, age=46), Teacher(id=2765763, name=name948, age=90), Teacher(id=2765764, name=name949, age=10), Teacher(id=2765765, name=name950, age=82), Teacher(id=2765766, name=name951, age=78), Teacher(id=2765767, name=name952, age=43), Teacher(id=2765768, name=name953, age=80), Teacher(id=2765769, name=name954, age=74), Teacher(id=2765770, name=name955, age=28), Teacher(id=2765771, name=name956, age=18), Teacher(id=2765772, name=name957, age=6), Teacher(id=2765773, name=name958, age=75), Teacher(id=2765774, name=name959, age=58), Teacher(id=2765775, name=name960, age=66), Teacher(id=2765776, name=name961, age=54), Teacher(id=2765777, name=name962, age=72), Teacher(id=2765778, name=name963, age=0), Teacher(id=2765779, name=name964, age=84), Teacher(id=2765780, name=name965, age=17), Teacher(id=2765781, name=name966, age=36), Teacher(id=2765782, name=name967, age=29), Teacher(id=2765783, name=name968, age=35), Teacher(id=2765784, name=name969, age=89), Teacher(id=2765785, name=name970, age=40), Teacher(id=2765786, name=name971, age=31), Teacher(id=2765787, name=name972, age=37), Teacher(id=2765788, name=name973, age=91), Teacher(id=2765789, name=name974, age=45), Teacher(id=2765790, name=name975, age=53), Teacher(id=2765791, name=name976, age=28), Teacher(id=2765792, name=name977, age=81), Teacher(id=2765793, name=name978, age=23), Teacher(id=2765794, name=name979, age=73), Teacher(id=2765795, name=name980, age=94), Teacher(id=2765796, name=name981, age=51), Teacher(id=2765797, name=name982, age=72), Teacher(id=2765798, name=name983, age=8), Teacher(id=2765799, name=name984, age=24), Teacher(id=2765800, name=name985, age=97), Teacher(id=2765801, name=name986, age=11), Teacher(id=2765802, name=name987, age=63), Teacher(id=2765803, name=name988, age=83), Teacher(id=2765804, name=name989, age=27), Teacher(id=2765805, name=name990, age=86), Teacher(id=2765806, name=name991, age=50), Teacher(id=2765807, name=name992, age=90), Teacher(id=2765808, name=name993, age=2), Teacher(id=2765809, name=name994, age=37), Teacher(id=2765810, name=name995, age=82), Teacher(id=2765811, name=name996, age=100), Teacher(id=2765812, name=name997, age=50), Teacher(id=2765813, name=name998, age=54), Teacher(id=2765814, name=name999, age=18)]
     *
     * 【结果】
     * 成功
     */
    @Test
    public void test1() {
        TestHelper.startTest("mybatis-plus集成测试");
        long startTime1 = System.currentTimeMillis();
        List<Teacher> teachers = teacherMapper.selectList(null);
        TestHelper.println("查询的结果:\n" + teachers);
        // 查询用时: 3730ms
        // 第一次查询会很慢
        TestHelper.println("查询用时", (System.currentTimeMillis() - startTime1) + "ms");

        for (int i = 0; i < 20; i++) {
            startTime1 = System.currentTimeMillis();
            teachers = teacherMapper.selectList(null);
            TestHelper.println("查询的结果:\n" + teachers);
            TestHelper.println("查询用时", (System.currentTimeMillis() - startTime1) + "ms");
        }
    }


    /**
     * 2.Mapper CRUD接口方法测试
     *
     * 2.1.demo: 增,有以下方法
     * int insert(T entity)
     *
     * 【注意】
     * 1.默认情况下,持久化对象主键id需要是long/Long类型,否则会报错:
     * 原因是mybatis-plus默认的主键生成策略会为我们生成long类型的主键。
     *
     * 2.如果需要使用数据库默认的主键自增策略,则为主键属性添加@TableId(type =IdType.AUTO)即可。
     *
     * 3.mybatis-plus默认的序列化/反序列化映射:
     * 3.1.将与表列名相同的Entity类属性进行序列化/反序列化。
     * 3.2.序列化/反序列化的表名由类名指定。
     *
     * 【缺点】
     * 1.只有一个insert方法,每次只能插入一条。批量插入数据时会影响系统性能。
     * 2.当实体对象所有属性都为null时,会导致语法错误异常: SQLSyntaxErrorException...
     */
    @Test
    public void insert() {

        /*
        1.insert测试: int insert(T entity)
         */
        Teacher insertTeacher = new Teacher();
        insertTeacher.setName("mp-insert-teacher");
        insertTeacher.setAge(22);
        /*
        发生了一个异常:
        org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: Could not set property 'id' of 'class com.zj.test.mp.po.Teacher' with value '1348562220438269953' Cause: java.lang.IllegalArgumentException: argument type mismatch

        原因: 由于mybatis-plus会自动插入一个id到实体对象, 不管你是否对id设置值都会向实体类中添加一个long类型的值, 所以有时候导致一些意外的情况发生

        解决:
        方式1:
        将id的类型改变成long类型，数据库改成和实体类对应的long类型。        //true
        当时测试时,向表中插入了一下数据:
        1348562924007702529	mp-insert-teacher	22      // 可见mybatis-plus默认情况下确实为我们生成了主键，而没有使用数据库主键自增

        方式2:
        如果还是想要使用数据库id自增, 就要把mybatis-plus这个id生成的功能给关掉，可以在id字段上加上如下注解即可:
        @TableId(type =IdType.AUTO)
        // 经测试: 使用该注解后,主键字段可以使用Integer,因为不会再分配long值,不会报错。
        // 记录一个小失误: 在将表主键id改为bigint后,使用@TableId(type =IdType.AUTO)无效，仍然会使用很长的long值作为主键,原因就是表AUTO_INCREMENT的值因为long类型的插入而变得很大,解决方法就是将AUTO_INCREMENT改为正常值就好了。
         */
        teacherMapper.insert(insertTeacher);
    }


    /**
     * 2.2.demo: 删除操作,有以下方法
     * int deleteById(Serializable id)      根据主键删除一条记录,想要使用该方法,实体类必须实现序列化接口
     *
     */
    @Test
    public void delete() {
        Teacher deleteTeacher = new Teacher();
        deleteTeacher.setId(2765815);
        /*
        1.测试: 根据主键删除记录
        int deleteById(Serializable id)
        会根据实体类中与表主键字段相同的属性值，删除表中的数据。

        缺点: 一次只能删除一条,在批量删除大量数据的时候返回调用该方法会影响系统性能。

        返回值: 如果存在指定的记录并删除,返回1, 如果没有指定的记录,返回0。

        注意: 想要使用该方法,实体类必须实现序列化接口。
         */
        int row1 = teacherMapper.deleteById(deleteTeacher);
        TestHelper.println("teacherMapper.deleteById(deleteTeacher)", row1);

        /*
        2.测试: 根据主键批量删除
        int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList);

        优点: 批量的。
        */
        // Arrays.asList: 一种快速的写法,不用new多个实体类对象
        int row2 = teacherMapper.deleteBatchIds(Arrays.asList(2765816, 2765817));
        TestHelper.println("teacherMapper.deleteById(deleteTeacher)", row2);

        /*
        3.测试: 多条件批量删除
        int deleteByMap(@Param("cm") Map<String, Object> columnMap);

        返回值: 返回根据条件删除的记录数。
         */
        // 创建几条数据用于删除
        Teacher deleteTeacher1 = new Teacher();
        Teacher deleteTeacher2 = new Teacher();
        Teacher deleteTeacher3 = new Teacher();

        deleteTeacher1.setName("teacher for delete");
        deleteTeacher1.setAge(22);
        deleteTeacher2.setName("teacher for delete");
        deleteTeacher2.setAge(22);
        deleteTeacher3.setName("teacher for delete");
        deleteTeacher3.setAge(21);

        teacherMapper.insert(deleteTeacher1);
        teacherMapper.insert(deleteTeacher2);
        teacherMapper.insert(deleteTeacher3);

        Map<String, Object> deleteMap = new HashMap<String, Object>();
        deleteMap.put("name", "teacher for delete");
        // 只删除年纪22岁的老师记录
        deleteMap.put("age", 22);
        int deleteRow = teacherMapper.deleteByMap(deleteMap);
        TestHelper.println("teacherMapper.deleteByMap(deleteMap)", deleteRow);

        /*
        4.测试: 根据条件删除- 更加复杂的条件判断
         */
        teacherMapper.insert(deleteTeacher1);
        teacherMapper.insert(deleteTeacher2);
        teacherMapper.insert(deleteTeacher3);

        // 这里使用QueryWrapper和UpdateWrapper都可以,没有看出区别,这个放在后面讨论
        // QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        UpdateWrapper<Teacher> queryWrapper = new UpdateWrapper<>();
        queryWrapper.eq("name", "teacher for delete").eq("age", 22);
        int delete = teacherMapper.delete(queryWrapper);
        TestHelper.println("teacherMapper.delete(queryWrapper)", delete);
    }


    /**
     * 2.3.demo: 更新, 有以下方法:
     * int updateById(@Param("et") T entity);   根据主键更新单条数据
     * int update(@Param("et") T entity, @Param("ew") Wrapper<T> updateWrapper);
     *              根据条件批量更新，需要注意的是entity的主键不会被序列化。
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void update() {
        /*
        1.demo: 根据主键修改指定的记录
        int updateById(@Param("et") T entity);

        返回值:0/1
         */
        Teacher teacher = new Teacher();
        teacher.setId(2765820);
        teacher.setName("update-teacher-name");
        int i = teacherMapper.updateById(teacher);
        TestHelper.println("teacherMapper.updateById(teacher)", i);

        /*
        2.demo: 根据条件批量更新
        int update(@Param("et") T entity, @Param("ew") Wrapper<T> updateWrapper);

        注意: entity主键字段不会被序列化。
         */
        Teacher updateTeacher = new Teacher();
        updateTeacher.setId(1111111);// 不会被序列化: 不会被更新到数据库。
        updateTeacher.setName("update-teacher-name5");
        // UpdateWrapper也可以
        QueryWrapper<Teacher> updateWrapper = new QueryWrapper<>();
        // 经测试, 主键也可以作为Wrapper的条件。
        updateWrapper.eq("name", "update-teacher-name4").eq("age", 21)/*.eq("id",2765820)*/;

        int update = teacherMapper.update(updateTeacher, updateWrapper);
        TestHelper.println("teacherMapper.update(updateTeacher, updateWrapper)", update);
    }

    /**
     * 2.4.测试: 查询数据, 方法比较多, 如下:
     * Integer selectCount(Wrapper<T> queryWrapper);
     * 根据条件查询数据条数
     *
     * <E extends IPage<T>> E selectPage(E page,Wrapper<T> queryWrapper);
     * 条件进行分页查询,返回结果封装在Page中,数据用实体类封装
     * <E extends IPage<Map<String, Object>>> E selectMapsPage(E page,Wrapper<T> queryWrapper);
     * 条件进行分页查询，结果封装在Page中，且数据用Map封装。
     *
     * List<T> selectList(Wrapper<T> queryWrapper);
     * 根据条件全量查询数据，结果封装在List中
     *
     * 根据ids批量获取, 结果封装在List中
     * List<77T> selectBatchIds(Collection<? extends Serializable> idList);
     *
     * T selectById(Serializable id);
     * 通过id查询单条记录
     *
     * List<T> selectByMap(@Param("cm") Map<String, Object> columnMap);
     * 多条件全量查询,条件用Map<String,Object>参数传递,结果封装在List中
     *
     * List<Object> selectObjs(Wrapper<T> queryWrapper);
     * 根据条件批量查询,返回数据的主键集合
     *
     * T selectOne(@Param("ew") Wrapper<T> queryWrapper);
     * 根据条件查询一条数据
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void select() {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<Teacher>()
                .eq("name", "update-teacher-name5");

        /*
        根据条件统计数据量:
        1.Integer selectCount(Wrapper<T> queryWrapper);
         */
        Integer integer = teacherMapper.selectCount(queryWrapper);
        TestHelper.println("name=update-teacher-name5的数据条数", integer);

        // current: 从1开始,1为第一页,<=0当做1来处理
        // 相当于Page queryPage = new Page(1, 3)
        Page queryPage = new Page(-333, 3);

        /*
        2.条件进行分页查询,返回结果封装在Page中,数据用实体类封装
        <E extends IPage<T>> E selectPage(E page,Wrapper<T> queryWrapper);

        注: 测试时分页无效果,待后续处理

        条件进行分页查询，结果封装在Page中，且数据用Map封装。
        <E extends IPage<Map<String, Object>>> E selectMapsPage(E page,Wrapper<T> queryWrapper);
         */
        Page page1 = teacherMapper.selectPage(queryPage, queryWrapper);
        TestHelper.println("teacherMapper.selectPage(queryPage, queryWrapper)", page1.getRecords());

        /*
        3.根据条件全量查询数据，结果封装在List中
        List<T> selectList(Wrapper<T> queryWrapper);
         */
        List<Teacher> teachers = teacherMapper.selectList(queryWrapper);
        TestHelper.println("teacherMapper.selectList(queryWrapper)共查询" + teachers.size() + "条数据", teachers);

        /*
        4.根据ids批量获取, 结果封装在List中
        List<T> selectBatchIds(Collection<? extends Serializable> idList);
         */
        TestHelper.println("teacherMapper.selectBatchIds(Arrays.asList(2765823,2765826))", teacherMapper.selectBatchIds(Arrays.asList(2765823, 2765826)));

        /*
        5.通过id查询单条记录
        T selectById(Serializable id);
        返回值: 如果对应主键的数据不存在,返回null,如果存在则返回实体对象。
         */
        TestHelper.println("teacherMapper.selectById(2765823)", teacherMapper.selectById(276582222));

        /*
        6.多条件全量查询,条件用Map<String,Object>参数传递,结果封装在List中
        List<T> selectByMap(@Param("cm") Map<String, Object> columnMap);
         */
        Map<String, Object> selectByMap = new HashMap<String, Object>();
        selectByMap.put("name", "update-teacher-name5");
        TestHelper.println("teacherMapper.selectByMap()", teacherMapper.selectByMap(selectByMap));

        /*
        7.根据条件批量查询,返回数据的主键集合
        List<Object> selectObjs(Wrapper<T> queryWrapper);

        注意:
        1.返回的数据是主键集合,数据引用类型是Object,实际对象类型为Long
         */
        TestHelper.println("teacherMapper.selectObjs(queryWrapper)", teacherMapper.selectObjs(queryWrapper));
        TestHelper.println("teacherMapper.selectObjs(queryWrapper).get(0).class", teacherMapper.selectObjs(queryWrapper).get(0).getClass());

        /*
        8.根据条件查询一条数据
        T selectOne(@Param("ew") Wrapper<T> queryWrapper);

        注意:
        1.根据条件必须只能查到1/0条记录, 否则报错:
        org.mybatis.spring.MyBatisSystemException:
            nested exception is org.apache.ibatis.exceptions.TooManyResultsException:
                Expected one result (or null) to be returned by selectOne(), but found: 7
         */
        // TestHelper.println("teacherMapper.selectOne(queryWrapper)", teacherMapper.selectOne(queryWrapper));
    }

    @Autowired
    PageMapper pageMapper;
    /**
     * 3.测试: mybatis xml无分页方式实现自定义sql
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void xmlNoPageTest(){

        // 1.自定义sql,无分页测试
        // 测试结果: 确实没有进行分页
        List<Teacher> teachers = pageMapper.queryNoPage("update-teacher-name5");
        TestHelper.println("无分页查询获取的数据条数",teachers.size());
        TestHelper.println("无分页查询获取的数据",teachers);
    }

    /**
     * 4.测试: mybatis-plus xml方式实现分页查询
     *
     * NOTE:
     * 如果返回类型为List则会将查询结果封装到List中,
     * 如果返回类型是Page则会将查询结果封装到Page中。
     */
    @Test
    public void xmlByPageTest(){
        Page<Teacher> page = new Page<Teacher>();
        page.setCurrent(1);
        page.setSize(2);

        /*
        1.测试: 查询结果封装到List中

        结果: 成功
         */
        List<Teacher> teachers = pageMapper.queryByPage(page, "update-teacher-name5");
        TestHelper.println("分页查询Page(1,2)查询数据条数",teachers.size());
        TestHelper.println("分页查询Page(1,2)查询到的数据",teachers);

        /*
        2.测试: 查询结果封装到IPage中

        结果: 成功

        注意: 官方教程中demo中提到Page参数必须放在第一个参数位置,我这里测试放在第二位，并没有什么问题。
         */
        IPage<Teacher> teachers1 = pageMapper.queryByPage2("update-teacher-name5",page);
        TestHelper.println("分页查询Page(1,2)查询数据条数",teachers1.getSize());
        TestHelper.println("分页查询Page(1,2)查询到的数据",teachers1.getRecords());
    }

    /**
     * 5.逻辑删除相关测试
     *
     * 5.1.测试: 带有逻辑删除的数据插入、删除操作
     *
     * 【测试输出】
     *
     * 【结论】
     * 1.删除时只是将表中逻辑删除标记字段设置为1, 不会真正删除数据。
     * 2.插入时,逻辑删除字段可以是0/1/NULL,如果为1则表示插入一条已经被标识为逻辑删除的数据。
     *
     */
    @Autowired
    LogicDeleteTeacherMapper logicDeleteTeacherMapper;
    @Test
    public void logicDeleteTest(){

        LogicDeleteTeacher logicDeleteTeacher = new LogicDeleteTeacher();
        logicDeleteTeacher.setName("teacher-for-logic-delete");
        logicDeleteTeacher.setAge(111);
        logicDeleteTeacher.setDeleted(null);

        // 插入一条数据用于逻辑删除
        // 注意: mybatis-plus不会为数据库表添加逻辑删除字段
        logicDeleteTeacherMapper.insert(logicDeleteTeacher);

        // 尝试逻辑删除
        // 结果:成功,表中flag属性由0->1
        logicDeleteTeacherMapper.deleteById(2765846);
    }

    /**
     * 5.2.测试: 带有逻辑删除的数据查询操作
     *
     * 测试前,表中相关数据如下:
     * 2765842	teacher-for-logic-delete	111 NULL
     * 2765843	teacher-for-logic-delete	111 NULL
     * 2765844	teacher-for-logic-delete	111	1
     * 2765845	teacher-for-logic-delete	111	1
     * 2765846	teacher-for-logic-delete	111	1
     * 2765847	teacher-for-logic-delete	111	0
     * 2765848	teacher-for-logic-delete	111	0
     * 2765849	teacher-for-logic-delete	111	0
     *
     * 【测试输出】
     *
     * 查询到的数据条数: 3
     * 查询到的数据: [LogicTeacher(id=2765847, name=teacher-for-logic-delete, age=111, deleted=0), LogicTeacher(id=2765848, name=teacher-for-logic-delete, age=111, deleted=0), LogicTeacher(id=2765849, name=teacher-for-logic-delete, age=111, deleted=0)]
     *
     * 【结论】
     * 1.只有逻辑删除字段为0的数据才会被返回,1或NULL的数据都不会返回。
     */
    @Test
    public void logicDeleteSelect(){
        // 尝试查询,观察flag=1的数据是否被返回
        LogicDeleteTeacher condition = new LogicDeleteTeacher();
        condition.setName("teacher-for-logic-delete");
        List<LogicDeleteTeacher> selectList = logicDeleteTeacherMapper.selectList(new QueryWrapper<LogicDeleteTeacher>()
                .eq("name", "teacher-for-logic-delete"));
        TestHelper.println("查询到的数据条数",selectList.size());
        TestHelper.println("查询到的数据:\n" + selectList);
    }

    /**
     * 5.3.带有逻辑删除的数据更新操作
     *
     * 更新操作之前的数据库相关数据:
     * 2765842	teacher-for-logic-delete	111 NULL
     * 2765843	teacher-for-logic-delete	111 NULL
     * 2765844	teacher-for-logic-delete	111	1
     * 2765845	teacher-for-logic-delete	111	1
     * 2765846	teacher-for-logic-delete	111	1
     * 2765847	teacher-for-logic-delete	111	0
     * 2765848	teacher-for-logic-delete	111	0
     * 2765849	teacher-for-logic-delete	111	0
     *
     * 【测试输出】
     * 更新数据条数: 3
     *
     * 【结论】
     * 1.只会更新flag=0(逻辑未删除)的数据，1和NULL的数据不会被修改。
     */
    @Test
    public void logicDeleteUpdate(){
        LogicDeleteTeacher logicDeleteTeacher = new LogicDeleteTeacher();
        logicDeleteTeacher.setName("new-name-for-logic-delete");
        int affectRows = logicDeleteTeacherMapper.update(logicDeleteTeacher, new UpdateWrapper<LogicDeleteTeacher>()
                .eq("name", "teacher-for-logic-delete"));

        TestHelper.println("更新数据条数",affectRows);
    }

    /**
     * 6.测试: 插入通用枚举
     *
     * 【测试输出】
     *
     * 数据库插入了数据:
     * 2765852	小明	13		0
     * 2765854	小红	13		1
     *
     * 【结论】
     * 1.注意，如果没有添加@EnumValue注解，则会插入枚举常量,在这里指的是MALE/FEMALE.
     */
    @Test
    public void insertCommonEnum(){
        Teacher insertTeacher = new Teacher();
        insertTeacher.setName("小明");
        insertTeacher.setAge(13);
        insertTeacher.setSex(SexEnum.MALE);
        int affectRows = teacherMapper.insert(insertTeacher);
        TestHelper.println("插入数据条数",affectRows);

        Teacher insertTeacher2 = new Teacher();
        insertTeacher.setName("小红");
        insertTeacher.setAge(13);
        insertTeacher.setSex(SexEnum.FEMALE);
        int affectRows2 = teacherMapper.insert(insertTeacher);
        TestHelper.println("插入数据条数",affectRows2);



    }
}
