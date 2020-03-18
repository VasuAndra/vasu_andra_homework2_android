package com.example.tema2;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class StudentRepository {

   private myAppDatabase appDatabase;

   public StudentRepository(Context context) {
            appDatabase = ApplicationController.getAppDatabase();
   }



   public void insertStudentAsync(final Student student,
                               final InsertStudentListener listener) {
            new InsertAsync(listener).execute(student);
   }

    public void getStudentsAsync(final GetStudentsListener listener) {
        new GetMarksAsync(listener).execute();
    }

    public void deleteStudentAsync(String name, final DeleteListener listener) {
        new DeleteAsync(listener).execute(name);
    }

    public Student getStudentByName(String name){

        return appDatabase.studentDAO().findByName(name);
    }



    private class InsertAsync extends AsyncTask<Student, Void, Void> {
        InsertStudentListener listener;
        InsertAsync(InsertStudentListener listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Student... users) {
            appDatabase.studentDAO().insertAll(users[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }


    private class GetMarksAsync extends AsyncTask<Void, Void, List<Student>> {
        GetStudentsListener listener;

        GetMarksAsync(GetStudentsListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Student> doInBackground(Void... voids) {
            return appDatabase.studentDAO().getAll();
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            listener.actionSuccess(students);
        }
    }



    private class DeleteAsync extends AsyncTask<String, Void, Boolean> {
        DeleteListener listener;

        DeleteAsync(DeleteListener listener) {
            this.listener = listener;
        }

        @Override
        protected Boolean doInBackground(String... names) {
            for(String name : names){
                Student student = getStudentByName(name);

                if(student == null){
                    return false;
                }

                appDatabase.studentDAO().delete(student);
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean wasSuccessful) {
            super.onPostExecute(wasSuccessful);
            listener.deleteResult(wasSuccessful);
        }
    }
}
