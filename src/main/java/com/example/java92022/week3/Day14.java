package com.example.java92022.week3;

/**
 *  student m - m teacher
 *  student 1 - m student_teacher m - 1 teacher
 *
 *  student(id(pk), name)
 *  student_teacher(id(pk), s_id(fk), t_id(fk))
 *  teacher(id(pk), name)
 *
 *
 *  select s.*, st.*, t.*
 *  from student s join student_teacher st on s.id = st.s_id
 *       join teacher t on st.t_id = t.id;
 *
 *  -----------*  -----------*  -----------*  -----------*  -----------*  -----------
 *   eager loading
 *  Student s1 = HQL/JPQL (select * from student where id = xx)
 *               select s.*, st.*
 *               from student s join student_teacher st on s.id = st.s_id
                 where id = xx
 *  List<Teacher_Student> ts = s1.getTeacher_students()
 *
 *
 *   lazy loading
 *   Student s1 = HQL/JPQL (select * from student where id = xx)
 *                select * from student where id = xx
 *   List<Teacher_Student> ts = s1.getTeacher_students()
 *                              select s.*, st.*
 *                              from student s join student_teacher st on s.id = st.s_id
 *                              where id = xx
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  lazy loading
 *  List<Student> students = ..   1 query
 *  for(Student s: students) {
 *      List<Teacher_Student> ts = s.getTeacher_students();
 *      ..
 *  }
 *  -----------*  -----------*  -----------*  -----------*  -----------
 *  hibernate => session factory => session
 *  jpa => entity manager factory => entity manager
 *
 *  spring data jpa (hibernate)
 *
 *  ------------
 *  persistent context
 *
 */

/**
 *  Network
 *  www.xx.com
 *      1. DNS -> public IP address
 *      2. build connection [Source IP + Source Port, Dest IP + Dest Port]
 *      3..
 *  OSI 7 layers
 *      Application
 *      Presentation
 *      Session
 *      Transport
 *      Network
 *      Data link
 *      Physical
 *  0.0.0.0 - 255.255.255.255
 *  private ip -> NAT(public ip pool) -> source public ip  -> destination public ip
 *                 public ip1 + port1                           public ip2 + port1
 *                 public ip1 + port2
 *
 *
 *              if(get) {
 *                  if(header =..) {
 *
 *                  }
 *              }
 *              else if()
 *              else if()
 *
 */