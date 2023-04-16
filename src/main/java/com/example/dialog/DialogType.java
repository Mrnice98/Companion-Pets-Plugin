package com.example.dialog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DialogType
{
	DIALOG_HEAD_LEFT(46, 52, 40, 0, 1882, 109),
	DIALOG_HEAD_RIGHT(426, 52, 40, 0, 166, 17),
	CHOICE(0, 0, 0, 0, 0, 0),
	;

	private final int headX;
	private final int headY;
	private final int headRotX;
	private final int headRotY;
	private final int headRotZ;

	private final int textX;

	public int getNameY()
	{
		return 16;
	}

	public int getTextY()
	{
		return 32;
	}

	public int getContinueY()
	{
		return 96;
	}
}
