

def get_data_row(file_name):
  first_numbers = ''
  second_numbers = ''
  third_numbers = ''
  fourth_numbers = ''
  fifth_numbers = ''

  with open(file_name, 'r') as f:
    for line in f:
      data = line.split()
      if len(data) >= 4:
        first_numbers = first_numbers + ' ' + data[0] + "\n"
        second_numbers = second_numbers + ' ' + data[1] + "\n"
        third_numbers = third_numbers + ' ' + data[2] + "\n"
        fourth_numbers = fourth_numbers + ' ' + data[3] + "\n"
        fifth_numbers = fifth_numbers + ' ' + data[4] + "\n"
  
  return first_numbers, second_numbers, third_numbers, fourth_numbers, fifth_numbers

file_name = "test_data.txt"
first_numbers, second_numbers, third_numbers, fourth_numbers, fifth_numbers = get_data_row(file_name)

print(fourth_numbers)


