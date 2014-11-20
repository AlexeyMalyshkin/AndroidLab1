package com.androidlab.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.androidlab.app.R;
import com.androidlab.app.constant.Priority;
import com.androidlab.app.domain.Note;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {

    private List<Note> list;
    private LayoutInflater inflater;
    private int layoutResourceId;
    private Context context;

    public NoteAdapter(List<Note> list, Context context, int layoutResourceId) {
        super(context, layoutResourceId, list);
        this.list = list;
        this.layoutResourceId = layoutResourceId;
        this.context = context;

        inflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Note getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(layoutResourceId, viewGroup, false);
        }

        Note note = getNote(position);

        TextView titleView = (TextView) view.findViewById(R.id.noteTitle);
        titleView.setText(note.getTitle());

        TextView dateView = (TextView) view.findViewById(R.id.noteDate);
        dateView.setText(note.getDateTime().toString());

        ImageView priorityImage = (ImageView) view.findViewById(R.id.priorityIgame);
        String priorityImageName = priorityImageName(list.get(position));
        Resources resources = context.getResources();
        priorityImage.setImageResource(resources.getIdentifier(priorityImageName, "drawable", (context).getPackageName()));

        ImageView galeryImage = (ImageView) view.findViewById(R.id.galeryImage);
//        String priorityImageName = priorityImageName(list.get(position));
//        Resources resources = context.getResources();
        galeryImage.setImageResource(resources.getIdentifier(note.getImageId(), "drawable", (context).getPackageName()));



        return view;
    }

    private String priorityImageName(Note note) {
        Priority priority = note.getPriority();
        if (priority == Priority.HIGH) {
            return "priority_high";
        } else if (priority == Priority.MIDDLE) {
            return "priority_middle";
        } else {
            return "priority_low";
        }
    }

    private Note getNote(int i) {
        return getItem(i);
    }
}

