import 'package:flutter/material.dart';
import 'package:milestone3/assets/MyColors.dart';

class CustomButton extends StatelessWidget {
  final String label;
  final VoidCallback onPressed;
  final Color color;
  final TextStyle textStyle;
  final double borderRadius;
  final EdgeInsets padding;

  const CustomButton({
    Key? key,
    required this.label,
    required this.onPressed,
    this.color = myColors.Grey_Medium,
    this.textStyle = const TextStyle(color: myColors.Grey_Light, fontSize: 20),
    this.borderRadius = 100.0,
    this.padding = const EdgeInsets.symmetric(vertical: 12, horizontal: 20),
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding( padding: const EdgeInsets.all(10.0),
      child: SizedBox(
        width: double.infinity,
        child: ElevatedButton(
          style: ElevatedButton.styleFrom(
            backgroundColor: color,
            foregroundColor: myColors.Grey_Dark,
            padding: padding,
            shape: RoundedRectangleBorder(
              side: BorderSide(color: myColors.Grey_Dark, width: 4),
              borderRadius: BorderRadius.circular(borderRadius),
            ),
          ),
          onPressed: onPressed,
          child: Text(
            label,
            style: textStyle,
          ),
        )
      )
    );
  }
}
