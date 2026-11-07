import 'package:flutter/material.dart';

class Student {
  String name;
  double? score;

  Student({required this.name, this.score});
}

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: const GradeListScreen(),
    );
  }
}

class GradeListScreen extends StatelessWidget {
  const GradeListScreen({super.key});

  String _getRank(double? score) {
    if (score == null) {
      return 'Chưa có điểm';
    } else if (score >= 8.0) {
      return 'Giỏi';
    } else if (score >= 6.5) {
      return 'Khá';
    } else if (score >= 5.0) {
      return 'Trung bình';
    } else {
      return 'Yếu';
    }
  }

  Color _getRankColor(String rank) {
    if (rank == 'Giỏi') {
      return Colors.green;
    } else if (rank == 'Khá') {
      return Colors.blue;
    } else if (rank == 'Trung bình') {
      return Colors.orange;
    } else if (rank == 'Yếu') {
      return Colors.red;
    } else {
      return Colors.grey;
    }
  }

  void _showStudentDetails(BuildContext context, Student student) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(student.name),
          content: Text(
            'Điểm số: ${student.score == null ? 'Chưa có điểm' : student.score.toString()}\nXếp loại: ${_getRank(student.score)}',
          ),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text('Đóng'),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    final List<Student> students = [
      Student(name: 'Nguyễn Văn An', score: 9.0),
      Student(name: 'Trần Thị Bình', score: 7.2),
      Student(name: 'Lê Minh Chí', score: null),
      Student(name: 'Phạm Quốc Dũng', score: 4.5),
      Student(name: 'Võ Thanh Em', score: 5.8),
    ];

    double totalScore = 0;
    int validCount = 0;
    int gioiCount = 0;
    int nullCount = 0;
    double maxScore = 0;

    for (var student in students) {
      if (student.score == null) {
        nullCount++;
      } else {
        totalScore += student.score!;
        validCount++;
        if (student.score! >= 8.0) {
          gioiCount++;
        }
        if (student.score! > maxScore) {
          maxScore = student.score!;
        }
      }
    }

    double avgScore = validCount > 0 ? totalScore / validCount : 0;

    List<Widget> studentCards = [];
    for (var student in students) {
      String rank = _getRank(student.score);
      Color rankColor = _getRankColor(rank);

      studentCards.add(
        GestureDetector(
          onTap: () => _showStudentDetails(context, student),
          child: Container(
            margin: const EdgeInsets.only(bottom: 8),
            padding: const EdgeInsets.all(12),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(8),
              border: Border.all(color: Colors.grey.shade300),
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      student.name,
                      style: const TextStyle(
                        fontSize: 15,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 4),
                    Text(
                      student.score == null ? 'Chưa có điểm' : rank,
                      style: TextStyle(
                        color: student.score == null ? Colors.grey : rankColor,
                        fontSize: 13,
                      ),
                    ),
                  ],
                ),
                Text(
                  student.score == null ? '—' : student.score.toString(),
                  style: const TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ],
            ),
          ),
        ),
      );
    }

    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xFF1E3A8A),
        title: const Text(
          'Bảng điểm lớp',
          style: TextStyle(color: Colors.white),
        ),
      ),
      body: Container(
        color: Colors.grey.shade100,
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                width: double.infinity,
                padding: const EdgeInsets.all(20),
                decoration: BoxDecoration(
                  color: const Color(0xFF1E3A8A),
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      'Điểm trung bình lớp',
                      style: TextStyle(color: Colors.white70, fontSize: 13),
                    ),
                    const SizedBox(height: 4),
                    Text(
                      avgScore.toStringAsFixed(2),
                      style: const TextStyle(
                        color: Colors.white,
                        fontSize: 32,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 16),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const Text('Giỏi', style: TextStyle(color: Colors.white70, fontSize: 12)),
                            const SizedBox(height: 2),
                            Text(
                              '$gioiCount',
                              style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 16),
                            ),
                          ],
                        ),
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const Text('Chưa có điểm', style: TextStyle(color: Colors.white70, fontSize: 12)),
                            const SizedBox(height: 2),
                            Text(
                              '$nullCount',
                              style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 16),
                            ),
                          ],
                        ),
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const Text('Cao nhất', style: TextStyle(color: Colors.white70, fontSize: 12)),
                            const SizedBox(height: 2),
                            Text(
                              '$maxScore',
                              style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 16),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 16),
              const Text(
                'DANH SÁCH - 5 SINH VIÊN',
                style: TextStyle(
                  color: Colors.grey,
                  fontWeight: FontWeight.bold,
                  fontSize: 12,
                ),
              ),
              const SizedBox(height: 8),
              Expanded(
                child: SingleChildScrollView(
                  child: Column(
                    children: studentCards,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}