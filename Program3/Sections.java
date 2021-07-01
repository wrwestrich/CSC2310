//Will Westrich

import java.util.ArrayList;

public class Sections
{
   private ArrayList<Section> sections;

   private double[] grade_constants;
   private int total_num_labs;

   //all sections should use the same random number generator
   private Random rand;
   
   public Sections(int num_sections, double[] grade_constants, int total_num_labs)
   {
      this.grade_constants = grade_constants;
      sections = new ArrayList<Section>();

      for (int i = 1; i <= num_sections; i++)
      {
         Section section = new Section(i, total_num_labs);
         sections.add(section);
      }

      rand = Random.getRandomNumberGenerator(); 
   }

   //DO THIS
   //several short methods must be implemented (note: some have been done, see below)

   public int getNumSections(){
        return sections.size();
   }

   public int getNumStudents(int sectionID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return -1;
        } 

        return sections.get(sectionID - 1).getNumStudents();
   }

   public int getNumLabs(int sectionID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return -1;
        }

        return sections.get(sectionID - 1).getNumLabs(sectionID);
   }

   public int getNumLabs(int sectionID, int studentID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return -1;
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return -1;
        }

        return sections.get(sectionID - 1).getNumLabs(studentID);
   }

   public int getPartnerID(int sectionID, int studentID, int labID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return -1;
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return -1;
        }
        if (labID <= 0 || labID > getNumLabs(sectionID)){
            return -1;
        }

        return sections.get(sectionID - 1).getPartnerID(studentID, labID);
   }

   public int getStudentID(int sectionID, String name){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return -1;
        }
        if (name == ""){
            return -1;
        }

        return sections.get(sectionID - 1).getStudentID(name);
   }

   public String getStudentName(int sectionID, int studentID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return "";
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return "";
        }

        return sections.get(sectionID - 1).getStudentName(studentID);
   }

   public boolean isPresent(int sectionID, int studentID, int labID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return false;
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return false;
        }
        if (labID <= 0 || labID > getNumLabs(sectionID)){
            return false;
        }

        return sections.get(sectionID - 1).isPresent(studentID, labID);
   }

   public String studentInfo(int sectionID, int studentID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return "";
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return "";
        }

        return sections.get(sectionID - 1).studentInfo(studentID);
   }

   public String partnerList(int sectionID, int labID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return "";
        }
        if (labID <= 0 || labID > getNumLabs(sectionID)){
            return "";
        }

        return sections.get(sectionID - 1).partnerList(labID);
   }

   public boolean isActive(int sectionID, int studentID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return false;
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return false;
        }

        return sections.get(sectionID - 1).isActive(studentID);
   }

   public void setInactive(int sectionID, int studentID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return;
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return;
        }

        sections.get(sectionID - 1).setInactive(studentID);
   }

   public void setNotPresent(int sectionID, int studentID, int labID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return;
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return;
        }
        if (labID <= 0 || labID > getNumLabs(sectionID)){
            return;
        }

        sections.get(sectionID - 1).setNotPresent(studentID, labID);
   }

   public void setGrade(int sectionID, int studentID, int labID, char grade){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return;
        }
        if (studentID <= 0 || studentID > getNumStudents(sectionID)){
            return;
        }
        if (labID <= 0 || labID > getNumLabs(sectionID)){
            return;
        }

        sections.get(sectionID - 1).setGrade(studentID, labID, grade);
   }

   public void addLab(int sectionID){

        if (sectionID <= 0 || sectionID > getNumSections()){
            return;
        }

        sections.get(sectionID - 1).addLab();
   }

   //no work below this point
   public void computePartners(int sectionID)
   {
      if (sectionID <= 0 || sectionID > getNumSections())
      {
         return;
      }

      Section section = sections.get(sectionID - 1);
      section.computePartners(rand);
   }

   public void writeFiles()
   {
      for (Section section : sections)
      {
         section.writeFile(grade_constants, total_num_labs);
      }
   }
}
